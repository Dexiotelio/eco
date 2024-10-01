package com.ecommerce.demo.enums;

public enum AddressErrorCode {
    ADDRESS_STREET_LENGTH_FAILURE("Street must be between 1 and 100 characters."),
    ADDRESS_STREET_NUMBER_LENGTH_FAILURE("Street number must be between 1 and 10 characters."),
    ADDRESS_APARTMENT_NUMBER_LENGTH_FAILURE("Apartment number cannot exceed 10 characters."),
    ADDRESS_NEIGHBORHOOD_LENGTH_FAILURE("Neighborhood must be between 1 and 50 characters."),
    ADDRESS_CITY_LENGTH_FAILURE("City must be between 1 and 50 characters."),
    ADDRESS_STATE_LENGTH_FAILURE("State must be between 1 and 50 characters."),
    ADDRESS_POSTAL_CODE_LENGTH_FAILURE("Postal code must be between 1 and 20 characters."),
    ADDRESS_COUNTRY_LENGTH_FAILURE("Country must be between 1 and 50 characters."),
    ADDRESS_ALREADY_EXISTS("The address already exists."),
    ADDRESS_CREATION_FAILURE("Failed to create the address."),
    ADDRESS_UPDATE_FAILURE("Failed to update the address."),
    ADDRESS_DELETE_FAILURE("Failed to delete the address.");

    private final String message;

    AddressErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
