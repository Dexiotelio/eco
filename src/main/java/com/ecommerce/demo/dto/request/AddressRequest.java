package com.ecommerce.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class AddressRequest {
    @NotBlank(message = "Street is required")
    @Size(max = 100, message = "Street cannot exceed 100 characters")
    private final String street;

    @NotBlank(message = "Street number is required")
    @Size(max = 10, message = "Street number cannot exceed 10 characters")
    private final String streetNumber;

    @Size(max = 10, message = "Apartment number cannot exceed 10 characters")
    private final String apartmentNumber;

    @NotBlank(message = "Neighborhood is required")
    @Size(max = 50, message = "Neighborhood cannot exceed 50 characters")
    private final String neighborhood;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private final String city;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State cannot exceed 50 characters")
    private final String state;

    @NotBlank(message = "Postal code is required")
    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    private final String postalCode;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country cannot exceed 50 characters")
    private final String country;

    @JsonCreator
    public AddressRequest(
            @JsonProperty("street") String street,
            @JsonProperty("streetNumber") String streetNumber,
            @JsonProperty("apartmentNumber") String apartmentNumber,
            @JsonProperty("neighborhood") String neighborhood,
            @JsonProperty("city") String city,
            @JsonProperty("state") String state,
            @JsonProperty("postalCode") String postalCode,
            @JsonProperty("country") String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }


    public AddressRequest(Builder builder) {
        this.street = builder.street;
        this.streetNumber = builder.streetNumber;
        this.apartmentNumber = builder.apartmentNumber;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    public static class Builder {
        private String street;
        private String streetNumber;
        private String apartmentNumber;
        private String neighborhood;
        private String city;
        private String state;
        private String postalCode;
        private String country;

        public Builder street(String street) { this.street = street; return this; }
        public Builder streetNumber(String streetNumber) { this.streetNumber = streetNumber; return this; }
        public Builder apartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; return this; }
        public Builder neighborhood(String neighborhood) { this.neighborhood = neighborhood; return this; }
        public Builder city(String city) { this.city = city; return this; }
        public Builder state(String state) { this.state = state; return this; }
        public Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }
        public Builder country(String country) { this.country = country; return this; }

        public AddressRequest build() {
            return new AddressRequest(this);
        }
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "AddressRequest{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
