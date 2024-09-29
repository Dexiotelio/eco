package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.request.UserRequest;
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
    private final AddressWriteRepositoryImpl addressWriteRepository;
    private final AddressQueryRepositoryImpl addressQueryRepository;

    @Autowired
    public UserWriteServicesImpl(UserWriteRepositoryImpl userWriteRepository,
                                 UserQueryRepositoryImpl userQueryRepository,
                                 AddressWriteRepositoryImpl addressWriteRepository,
                                 AddressQueryRepositoryImpl addressQueryRepository) {
        this.userWriteRepository = userWriteRepository;
        this.userQueryRepository = userQueryRepository;
        this.addressWriteRepository = addressWriteRepository;
        this.addressQueryRepository = addressQueryRepository;
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
            Address address = Address.toAddress(addressRequest);
            Result<Void> addressCreationResult = addressWriteRepository.create(address);
            if (!addressCreationResult.isSuccess()) {
                userWriteRepository.delete(user.getId());
                return Result.failure("Failed to create address: "
                        + String.join(", ", userCreationResult.getErrors()));
            }
        }
    }

    @Override
    @Transactional
    public Result<UserResponse> update(UserRequest request) {
        return Result.success(new UserResponse());
    }

    @Override
    @Transactional
    public Result<UserResponse> delete(UserRequest request) {
        return Result.success(new UserResponse());
    }
}
