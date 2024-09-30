package com.ecommerce.demo.enums;

public enum ErrorCode {
    VALIDATION_ID_REQUIRED("Id is required"),
    VALIDATION_FIRST_NAME_REQUIRED("First name is required"),
    VALIDATION_FIRST_NAME_LENGTH("First name cannot exceed 50 characters"),
    VALIDATION_LAST_NAME_REQUIRED("Last name is required"),
    VALIDATION_LAST_NAME_LENGTH("Last name cannot exceed 50 characters"),
    VALIDATION_USERNAME_REQUIRED("Username is required"),
    VALIDATION_USERNAME_LENGTH("Username cannot exceed 30 characters"),
    VALIDATION_USERNAME_PATTERN("Username can only contain alphanumeric characters and underscores"),
    VALIDATION_EMAIL_REQUIRED("Email is required"),
    VALIDATION_EMAIL_INVALID("Email should be valid"),
    VALIDATION_PASSWORD_REQUIRED("Password is required"),
    VALIDATION_PASSWORD_LENGTH("Password must be at least 8 characters long"),
    VALIDATION_AGE_REQUIRED("Age is required"),
    VALIDATION_AGE_MIN("Age must be greater than 18"),
    VALIDATION_GENDER_REQUIRED("Gender is required"),
    VALIDATION_PHONES_REQUIRED("Phones are required"),
    VALIDATION_ROLE_REQUIRED("Role is required"),
    VALIDATION_USERNAME_TAKEN("Username is already taken"),
    VALIDATION_EMAIL_TAKEN("Email is already registered"),
    VALIDATION_PHONE_FORMAT("Phone number format is invalid"),
    VALIDATION_ROLE_INVALID("Provided role is invalid"),
    VALIDATION_ADDRESS_REQUIRED("At least one address is required"),
    VALIDATION_ADDRESS_FORMAT("Address format is invalid");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
