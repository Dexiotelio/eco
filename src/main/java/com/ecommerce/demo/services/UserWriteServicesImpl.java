package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.AddressResponse;
import com.ecommerce.demo.dto.response.UserResponse;
import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.enums.UserErrorCode;
import com.ecommerce.demo.repositories.UserQueryRepositoryImpl;
import com.ecommerce.demo.repositories.UserWriteRepositoryImpl;
import com.ecommerce.demo.services.interfaces.UserWriteServices;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

// Service implementation for user writing operations
@Service
public class UserWriteServicesImpl implements UserWriteServices {
    private static final Logger logger = LoggerFactory.getLogger(UserWriteServicesImpl.class);

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
            logger.warn("Errores de validación para el usuario: {}", userValidation.getLeft());
            return Result.failure(userValidation.getLeft()); // Return validation errors if present
        }

        // Check if the user already exists based on the email
        Try<Result<Boolean>> userExists = userQueryRepository.exists(request.getEmail());
        if (userExists.isFailure()) {
            logger.error("Error al verificar la existencia del usuario: {}", userExists.getCause().getMessage());
            return Result.failure(userExists.getCause().getMessage()); // Handle failure in querying
        }

        Result<Boolean> userExistsResult = userExists.get();
        if (userExistsResult.isSuccess() && Boolean.TRUE.equals(userExistsResult.getValue())) {
            logger.warn("El usuario ya existe: {}", request.getEmail());
            return Result.failure(UserErrorCode.USER_ALREADY_EXISTS.getMessage()); // Return error if user exists
        }

        // Check if the username already exists
        Try<Result<Boolean>> usernameExists = userQueryRepository.existsUsername(request.getUserName());
        if (usernameExists.isFailure()) {
            logger.error("Error al verificar la existencia del nombre de usuario: {}", usernameExists.getCause().getMessage());
            return Result.failure(usernameExists.getCause().getMessage()); // Handle failure in querying
        }

        Result<Boolean> usernameExistsResult = usernameExists.get();
        if (usernameExistsResult.isSuccess() && Boolean.TRUE.equals(usernameExistsResult.getValue())) {
            logger.warn("El nombre de usuario ya existe: {}", request.getUserName());
            return Result.failure(UserErrorCode.USER_USERNAME_EXISTS.getMessage()); // Return error if username exists
        }

        // Create a new user from the request
        User user = User.toUser(request);
        Result<Long> userCreationResult = userWriteRepository.create(user);
        if (userCreationResult.isFailure()) {
            logger.error("Error al crear el usuario: {}", userCreationResult.getErrors());
            return Result.failure(UserErrorCode.USER_CREATION_FAILURE.getMessage() + ": "
                    + String.join(", ", userCreationResult.getErrors())); // Return error if user creation fails
        }

        Long userId = userCreationResult.getValue();
        logger.info("Usuario creado con éxito: ID {}", userId);
        // Create addresses associated with the user
        for (AddressRequest addressRequest : request.getAddress()) {
            Result<AddressResponse> addressResponseResult = addressWriteServices.create(userId, addressRequest);
            if (addressResponseResult.isFailure()) {
                // Uncomment to delete user if address creation fails
                String errorsMessages = String.join(", ", addressResponseResult.getErrors());
                logger.error("Error al crear la dirección para el usuario ID {}: {}", userId, errorsMessages);
                return Result.failure(String.format(
                        UserErrorCode.USER_ADDRESS_CREATION_FAILURE.getMessage(), errorsMessages));
            }
        }

        // Convert the user entity to response format and return success
        UserResponse userResponse = UserResponse.toUserResponse(user);
        logger.info("Proceso de creación de usuario finalizado con éxito: {}", userResponse);
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
