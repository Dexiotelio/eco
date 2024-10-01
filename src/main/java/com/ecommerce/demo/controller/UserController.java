package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.UserResponse;
import com.ecommerce.demo.services.UserWriteServicesImpl;
import com.ecommerce.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserWriteServicesImpl userWriteServices;

    @Autowired
    public UserController(UserWriteServicesImpl userWriteServices) {
        this.userWriteServices = userWriteServices;
    }

    @PostMapping("/create")
    public ResponseEntity<Result<?>> createUser(@RequestBody UserRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Set<String> errors = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toSet());
            return ResponseEntity.badRequest().body(Result.failure(errors));
        }

        Result<UserResponse> response = userWriteServices.create(request);
        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(Result.failure(response.getErrors()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Result.success("User successfully created: " + response));
    }
}