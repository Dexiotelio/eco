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

// Service implementation for user writing operations
@Service
public class UserWriteServicesImpl implements UserWriteServices {
    private final UserWriteRepositoryImpl userWriteRepository;
    private final UserQueryRepositoryImpl userQueryRepository;
    private final AddressWriteServicesImpl addressWriteServices;

    // Constructor that injects the required repositories and services
    @Autowired
    public UserWriteServicesImpl(UserWriteRepositoryImpl userWriteRepository,
                                 UserQueryRepositoryImpl userQueryRepository,
                                 AddressWriteServicesImpl addressWriteServices) {
        this.userWriteRepository = userWriteRepository;
        this.userQueryRepository = userQueryRepository;
        this.addressWriteServices = addressWriteServices;
    }

    @Override
    @Transactional // Ensures that the method is executed within a transaction
    public Result<UserResponse> create(UserRequest request) {
        // Validate the user request
        Either<Set<String>, UserRequest> userValidation = UserValidation.validateUserRequest(request);
        if (userValidation.isLeft()) {
            return Result.failure(userValidation.getLeft()); // Return validation errors if present
        }

        // Check if the user already exists based on the email
        Try<Result<Boolean>> userExists = userQueryRepository.exists(request.getEmail());
        if (userExists.isFailure()) {
            return Result.failure(userExists.getCause().getMessage()); // Handle failure in querying
        }

        Result<Boolean> userExistsResult = userExists.get();
        if (userExistsResult.isSuccess() && Boolean.TRUE.equals(userExistsResult.getValue())) {
            return Result.failure(UserErrorCode.USER_ALREADY_EXISTS.getMessage()); // Return error if user exists
        }

        // Check if the username already exists
        Try<Result<Boolean>> usernameExists = userQueryRepository.existsUsername(request.getUserName());
        if (usernameExists.isFailure()) {
            return Result.failure(usernameExists.getCause().getMessage()); // Handle failure in querying
        }

        Result<Boolean> usernameExistsResult = usernameExists.get();
        if (usernameExistsResult.isSuccess() && Boolean.TRUE.equals(usernameExistsResult.getValue())) {
            return Result.failure(UserErrorCode.USER_USERNAME_EXISTS.getMessage()); // Return error if username exists
        }

        // Create a new user from the request
        User user = User.toUser(request);
        Result<Long> userCreationResult = userWriteRepository.create(user);
        if (userCreationResult.isFailure()) {
            return Result.failure(UserErrorCode.USER_CREATION_FAILURE.getMessage() + ": "
                    + String.join(", ", userCreationResult.getErrors())); // Return error if user creation fails
        }

        Long userId = userCreationResult.getValue();
        // Create addresses associated with the user
        for (AddressRequest addressRequest : request.getAddress()) {
            Result<AddressResponse> addressResponseResult = addressWriteServices.create(userId, addressRequest);
            if (addressResponseResult.isFailure()) {
                // Uncomment to delete user if address creation fails
                String errorsMessages = String.join(", ", addressResponseResult.getErrors());
                return Result.failure(String.format(
                        UserErrorCode.USER_ADDRESS_CREATION_FAILURE.getMessage(), errorsMessages));
            }
        }
        // Convert the user entity to response format and return success
        UserResponse userResponse = UserResponse.toUserResponse(user);
        return Result.success(userResponse);
    }

    @Override
    @Transactional // Ensures that the method is executed within a transaction
    public Result<UserResponse> update(UserRequest request) {
        User user = User.toUser(request); // Convert request to user entity
        UserResponse userResponse = UserResponse.toUserResponse(user); // Convert user entity to response format
        return Result.success(userResponse); // Return success result
    }

    @Override
    @Transactional // Ensures that the method is executed within a transaction
    public Result<UserResponse> delete(UserRequest request) {
        User user = User.toUser(request); // Convert request to user entity
        UserResponse userResponse = UserResponse.toUserResponse(user); // Convert user entity to response format
        return Result.success(userResponse); // Return success result
    }
}
