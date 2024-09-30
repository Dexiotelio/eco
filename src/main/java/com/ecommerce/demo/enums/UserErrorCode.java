package com.ecommerce.demo.enums;

public enum UserErrorCode {
    USER_ALREADY_EXISTS("The user already exists"),
    USER_CREATION_FAILURE("Failed to create user");

    private final String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}