package com.ecommerce.demo.validation;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.enums.AddressErrorCode;
import com.ecommerce.demo.util.Dictionary;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class AddressValidation {
    private static final Logger logger = LoggerFactory.getLogger(AddressValidation.class);

    private AddressValidation() {}

    public static Either<Set<String>, AddressRequest> validateAddressRequest(AddressRequest request) {
        Dictionary<AddressRequest> validation = new Dictionary<>();

        validation.addValidationRule(
                req -> isLengthValid(request.getStreet(), 1, 100),
                AddressErrorCode.ADDRESS_STREET_LENGTH_FAILURE.getMessage()
        );
        validation.addValidationRule(
                req -> isLengthValid(request.getStreetNumber(), 1, 10),
                AddressErrorCode.ADDRESS_STREET_NUMBER_LENGTH_FAILURE.getMessage()
        );
        validation.addValidationRule(
                req -> isLengthValid(request.getNeighborhood(), 1, 50),
                AddressErrorCode.ADDRESS_NEIGHBORHOOD_LENGTH_FAILURE.getMessage()
        );
        validation.addValidationRule(
                req -> isLengthValid(request.getCity(), 1, 50),
                AddressErrorCode.ADDRESS_CITY_LENGTH_FAILURE.getMessage()
        );
        validation.addValidationRule(
                req -> isLengthValid(request.getState(), 1, 50),
                AddressErrorCode.ADDRESS_STATE_LENGTH_FAILURE.getMessage()
        );
        validation.addValidationRule(
                req -> isLengthValid(request.getPostalCode(), 1, 20),
                AddressErrorCode.ADDRESS_POSTAL_CODE_LENGTH_FAILURE.getMessage()
        );
        validation.addValidationRule(
                req -> isLengthValid(request.getCountry(), 1, 50),
                AddressErrorCode.ADDRESS_COUNTRY_LENGTH_FAILURE.getMessage()
        );
        return validation.validate(request);
    }

    private static boolean isLengthValid(String value, int min, int max) {
        return value != null && value.length() >= min && value.length() <= max;
    }
}
