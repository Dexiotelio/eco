package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.AddressResponse;
import com.ecommerce.demo.dto.response.UserResponse;
import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.repositories.AddressQueryRepositoryImpl;
import com.ecommerce.demo.repositories.AddressWriteRepositoryImpl;
import com.ecommerce.demo.repositories.UserQueryRepositoryImpl;
import com.ecommerce.demo.repositories.UserWriteRepositoryImpl;
import com.ecommerce.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (!userQueryRepository.exists(request.getId()).isSuccess()) {
            return Result.failure("The user already exist");
        }

       User user = User.toUser(request);
        Result<Void> userCreationResult = userWriteRepository.create(user);
        if (!userCreationResult.isSuccess()) {
            return Result.failure("Failed to create user: "
                    + String.join(", ", userCreationResult.getErrors()));
        }

        for (AddressRequest addressRequest: request.getAddress()) {
           Result<AddressResponse> addressResponseResult = addressWriteServices.create(addressRequest);
           if (!addressResponseResult.isSuccess()) {
               return Result.failure("Errors when creating address: " +
                       String.join(", ", addressResponseResult.getErrors()));
           }
        }

        UserResponse userResponse = UserResponse.toUserResponse(user);
        return Result.success(userResponse);
    }

    @Override
    @Transactional
    public Result<UserResponse> update(UserRequest request) {
    }

    @Override
    @Transactional
    public Result<UserResponse> delete(UserRequest request) {
    }
}
