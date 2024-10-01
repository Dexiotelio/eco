package com.ecommerce.demo.util;

import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.ecommerce.demo.enums.UserErrorCode;

import java.util.Set;

public class UserValidation {
    public static String validateUserRequest(UserRequest request) {
        Dictionary<UserRequest> validation = new Dictionary<>();

        validation.put(
                req -> validateLength(request.getFirstName(), 3, 50),
                req -> UserErrorCode.USER_FIRSTNAME_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validateLength(request.getLastName(), 3, 50),
                req -> UserErrorCode.USER_LASTNAME_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validateLength(request.getUserName(), 3, 50),
                req -> UserErrorCode.USER_USERNAME_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validateLength(request.getPassword(), 10),
                req -> UserErrorCode.USER_PASSWORD_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validatePhones(request.getPhones()),
                req -> UserErrorCode.USER_PHONES_FAILURE.getMessage()
        );
        validation.put(
                req -> validateContent(request.getGender()),
                req -> UserErrorCode.USER_GENDER_FAILURE.getMessage()
        );
        validation.put(
                req -> validateContent(request.getRole()),
                req -> UserErrorCode.USER_ROLE_FAILURE.getMessage();
        );
        validation.put(
                req -> validateEmail(request.getEmail()),
                req -> UserErrorCode.USER_EMAIL_FORMAT_FAILURE.getMessage()
        );
    }



    private static boolean validateLength(String value, int min, int max) {
        return value.length() >= min && value.length() <= max;
    }

    private static boolean validateLength(String value, int min) {
        return value.length() <= min;
    }

    private static boolean validatePhones(Set<String> value) {
        return value != null && !value.isEmpty();
    }

    private static boolean validateContent(Gender gender) {
        return gender != null;
    }

    private static boolean validateContent(Role role) {
        return role != null;
    }

    private static boolean validateEmail(String mail) {
        return mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
