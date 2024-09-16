package com.ecommerce.demo.dto.response;

import java.time.ZonedDateTime;

public class AddressResponse {
    public static class Builder {
        private Long id;
        private String street;
        private String streetNumber;
        private String apartmentNumber;
        private String neighborhood;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private String phone;
        private String addressType;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder street(String street) { this.street = street; return this; }
        public Builder streetNumber(String streetNumber) { this.streetNumber = streetNumber; return this; }
        public Builder apartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; return this; }
        public Builder neighborhood(String neighborhood) { this.neighborhood = neighborhood; return this; }
        public Builder city(String city) { this.city = city; return this; }
        public Builder state(String state) { this.state = state; return this; }
        public Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder addressType(String addressType) { this.addressType = addressType; return this; }

        public AddressResponse build() {
            return new AddressResponse(this);
        }
    }

    private final Long id;
    private final String street;
    private final String streetNumber;
    private final String apartmentNumber;
    private final String neighborhood;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;
    private final String phone;
    private final String addressType;

    private AddressResponse(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.streetNumber = builder.streetNumber;
        this.apartmentNumber = builder.apartmentNumber;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.phone = builder.phone;
        this.addressType = builder.addressType;
    }

    public Long getId() {
        return id;
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

    public String getPhone() {
        return phone;
    }

    public String getAddressType() {
        return addressType;
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}
