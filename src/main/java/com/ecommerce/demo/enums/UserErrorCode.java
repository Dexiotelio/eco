package com.ecommerce.demo.enums;

public enum UserErrorCode {
    USER_ALREADY_EXISTS("The user already exists."),
    USER_CREATION_FAILURE("Failed to create user."),
    USER_AGE_FAILURE("Age must be greater than 18."),
    USER_USERNAME_EXISTS("The username already exists."),
    USER_FIRSTNAME_LENGTH_FAILURE("First name must be between 3 and 50 characters."),
    USER_LASTNAME_LENGTH_FAILURE("Last name must be between 3 and 50 characters."),
    USER_USERNAME_LENGTH_FAILURE("User name must be between 3 and 50 characters."),
    USER_PASSWORD_LENGTH_FAILURE("Password must be at least 8 characters long."),
    USER_GENDER_FAILURE("Gender must be 'male', 'female', or 'other'."),
    USER_ROLE_FAILURE("Role must be 'client', 'admin', or 'visitor'."),
    USER_PHONES_FAILURE("At least one phone number is required."),
    USER_EMAIL_FORMAT_FAILURE("Email format is invalid.");

    private final String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}