package com.ecommerce.demo.enums;

public enum BusinessError {
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists");

    private final String message;

    BusinessError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
