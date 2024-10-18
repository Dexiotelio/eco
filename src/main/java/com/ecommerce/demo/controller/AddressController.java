package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.services.AddressWriteServicesImpl;
import com.ecommerce.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressWriteServicesImpl addressWriteServices;

    @Autowired
    public AddressController(AddressWriteServicesImpl addressWriteServices) {
        this.addressWriteServices = addressWriteServices;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createAddress(@PathVariable Long id, @RequestBody AddressRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect error messages and return them as a response
            Set<String> errors = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toSet());
            return ResponseEntity.badRequest().body(Result.failure(errors)); // Return a 400 Bad Request
        }

        Result<?> response = addressWriteServices.create(id, request);

        if (response.isFailure()) {
            return ResponseEntity.badRequest().body(Result.failure(response.getErrors())); // Return a 400 with errors
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Result.success("User successfully created: " + response)); // Return the success result
    }
}
