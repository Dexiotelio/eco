package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.AddressResponse;
import com.ecommerce.demo.dto.response.UserResponse;
import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.enums.UserErrorCode;
import com.ecommerce.demo.repositories.AddressQueryRepositoryImpl;
import com.ecommerce.demo.repositories.AddressWriteRepositoryImpl;
import com.ecommerce.demo.repositories.UserQueryRepositoryImpl;
import com.ecommerce.demo.repositories.UserWriteRepositoryImpl;
import com.ecommerce.demo.util.Result;
import com.ecommerce.demo.util.UserValidation;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;

@Service
public class UserWriteServicesImpl implements UserWriteServices {
    private final UserWriteRepositoryImpl userWriteRepository;
    private final UserQueryRepositoryImpl userQueryRepository;
    private final AddressWriteServicesImpl addressWriteServices;

    @Autowired
    public UserWriteServicesImpl(UserWriteRepositoryImpl userWriteRepository,
                                 UserQueryRepositoryImpl userQueryRepository,
                                 AddressWriteServicesImpl addressWriteServices
                                 ) {
        this.userWriteRepository = userWriteRepository;
        this.userQueryRepository = userQueryRepository;
        this.addressWriteServices = addressWriteServices;
    }

    @Override
    @Transactional
    public Result<UserResponse> create(UserRequest request) {
        Either<Set<String>, UserRequest> userValidationResult = UserValidation.validateUserRequest(request);
        if (userValidationResult.isLeft()) {
            return Result.failure(userValidationResult.getLeft());
        }

        Try<Result<Boolean>> userExists = userQueryRepository.exists(request.getEmail());
        if (userExists.isFailure()) {
            return Result.failure(userExists.getCause().getMessage());
        }

        Result<Boolean> userExistsResult = userExists.get();
        if (userExistsResult.isSuccess() && userExistsResult.getValue()) {
            return Result.failure(UserErrorCode.USER_ALREADY_EXISTS.getMessage());
        }

        Try<Result<Boolean>> usernameExists = userQueryRepository.existsUsername(request.getUserName());
        if (usernameExists.isFailure()) {
            return Result.failure(usernameExists.getCause().getMessage());
        }

        Result<Boolean> usernameExistsResult = usernameExists.get();
        if (usernameExistsResult.isSuccess() && usernameExistsResult.getValue()) {
            return Result.failure(UserErrorCode.USER_USERNAME_EXISTS.getMessage());
        }

        User user = User.toUser(request);
        Result<Void> userCreationResult = userWriteRepository.create(user);
        if (userCreationResult.isFailure()) {
            return Result.failure(UserErrorCode.USER_CREATION_FAILURE.getMessage() + ": "
                    + String.join(", ", userCreationResult.getErrors()));
        }

        for (AddressRequest addressRequest: request.getAddress()) {
           Result<AddressResponse> addressResponseResult = addressWriteServices.create(addressRequest);
           if (addressResponseResult.isFailure()) {
               return Result.failure(addressResponseResult.getErrors());
           }
        }

        UserResponse userResponse = UserResponse.toUserResponse(user);
        return Result.success(userResponse);
    }

    @Override
    @Transactional
    public Result<UserResponse> update(UserRequest request) {
        User user = User.toUser(request);
        UserResponse userResponse = UserResponse.toUserResponse(user);
        return Result.success(userResponse);
    }

    @Override
    @Transactional
    public Result<UserResponse> delete(UserRequest request) {
        User user = User.toUser(request);
        UserResponse userResponse = UserResponse.toUserResponse(user);
        return Result.success(userResponse);
    }
}
