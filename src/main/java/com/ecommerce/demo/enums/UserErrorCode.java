package com.ecommerce.demo.enums;

public enum UserErrorCode {
    USER_ALREADY_EXISTS("The user already exists"),
    USER_CREATION_FAILURE("Failed to create user"),
    USER_AGE_FAILURE("Age must be greater than 18"),
    USER_USERNAME_EXISTS("The username already exists");

    private final String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}