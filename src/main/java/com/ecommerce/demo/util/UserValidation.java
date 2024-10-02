package com.ecommerce.demo.util;

import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.ecommerce.demo.enums.UserErrorCode;
import io.vavr.control.Either;

import java.util.Set;

public class UserValidation {

    private UserValidation() {}

    public static Either<Set<String>, UserRequest> validateUserRequest(UserRequest request) {
        Dictionary<UserRequest> validation = new Dictionary<>();

        validation.put(
                req -> validateLength(request.getFirstName(), 3, 50),
                UserErrorCode.USER_FIRSTNAME_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validateLength(request.getLastName(), 3, 50),
                UserErrorCode.USER_LASTNAME_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validateLength(request.getUserName(), 3, 50),
                UserErrorCode.USER_USERNAME_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validateLength(request.getPassword(), 10, 30),
                UserErrorCode.USER_PASSWORD_LENGTH_FAILURE.getMessage()
        );
        validation.put(
                req -> validatePhonesSize(request.getPhones()),
                UserErrorCode.USER_PHONES_FAILURE.getMessage()
        );
        validation.put(
                req -> validatePhonesMatch(request.getPhones()),
                UserErrorCode.USER_PHONE_FORMAT_FAILURE.getMessage()
        );
        validation.put(
                req -> validateContent(request.getGender()),
                UserErrorCode.USER_GENDER_FAILURE.getMessage()
        );
        validation.put(
                req -> validateContent(request.getRole()),
                UserErrorCode.USER_ROLE_FAILURE.getMessage()
        );
        validation.put(
                req -> validateEmail(request.getEmail()),
                UserErrorCode.USER_EMAIL_FORMAT_FAILURE.getMessage()
        );
        validation.put(
                req -> validationAge(request.getAge()),
                UserErrorCode.USER_AGE_FAILURE.getMessage()
        );
        return validation.evaluate(request);
    }

    private static boolean validateLength(String value, int min, int max) {
        return value.length() >= min && value.length() <= max;
    }

    private static boolean validateLength(String value, int min) {
        return value.length() <= min;
    }

    private static boolean validatePhonesMatch(Set<String> phones) {
        return phones.stream().allMatch(p -> p.matches("^\\+\\d{1,3}\\d{4,14}$"));
    }

    private static boolean validatePhonesSize(Set<String> phones) {
        return !phones.isEmpty() && phones.size() <= 2;
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

    private static boolean validationAge(int age) {
        return age >= 18;
    }
}
