package com.ecommerce.demo.controller;

// Import necessary classes for handling requests and responses in the controller
import com.ecommerce.demo.dto.error.ErrorResponse;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.UserResponse;
import com.ecommerce.demo.services.UserWriteServicesImpl;
import com.ecommerce.demo.util.Result;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// Controller responsible for user management
@Controller
@RequestMapping("/users") // Define the base path for user-related requests
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Service to handle user creation logic
    private final UserWriteServicesImpl userWriteServices;

    // Constructor that injects the user write service
    @Autowired
    public UserController(UserWriteServicesImpl userWriteServices) {
        this.userWriteServices = userWriteServices;
    }

    // Method to create a new user
    @PostMapping("/create") // Maps the POST request to create a user
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest request) {
        // Call the service to create the user and store the response
        Result<UserResponse> response = userWriteServices.create(request);
        // Check if user creation was successful
        if (response.isFailure()) {
            logger.error("User creation failed: {}", response.getErrors());
            return ResponseEntity.badRequest().body(
                    new ErrorResponse(
                            "User Creation Failed", response.getErrors(), null, null)); // Return a 400 with errors
        }
        // Return a 201 Created response if the user was successfully created
        logger.info("User successfully created: {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Result.success("User successfully created: " + response)); // Return the success result
    }
}
