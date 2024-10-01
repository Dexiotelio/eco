package com.ecommerce.demo.enums;

public enum AddressErrorCode {
    ADDRESS_ALREADY_EXISTS("The address already exists"),
    ADDRESS_CREATION_FAILURE("Failed to create address");

    private final String message;

    AddressErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
