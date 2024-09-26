package com.ecommerce.demo.entities;

import java.time.ZonedDateTime;

public class Address {
    private Long userId;
    private String street;
    private String streetNumber;
    private String apartmentNumber;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String addressType;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public Address(Builder builder) {
        this.street = builder.street;
        this.streetNumber = builder.streetNumber;
        this.apartmentNumber = builder.apartmentNumber;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.addressType = builder.addressType;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public static class Builder {
        private Long userId;
        private String street;
        private String streetNumber;
        private String apartmentNumber;
        private String neighborhood;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private String addressType;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder userId(Long userId) { this.userId = userId; return this; }
        private Builder street(String street) { this.street = street; return this; }
        private Builder streetNumber(String streetNumber) { this.streetNumber = streetNumber; return this; }
        private Builder apartmentNumber(String apartmentNumber) { this.apartmentNumber = apartmentNumber; return this; }
        private Builder neighborhood(String neighborhood) { this.neighborhood = neighborhood; return this; }
        private Builder city(String city) { this.city = city; return this; }
        private Builder state(String state) { this.state = state; return this; }
        private Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }
        private Builder country(String country) { this.country = country; return this; }
        private Builder addressType(String addressType) { this.addressType = addressType; return this; }
        private Builder createdAt(ZonedDateTime createdAt) { this.createdAt = createdAt; return this; }
        private Builder updatedAt(ZonedDateTime updatedAt) { this.updatedAt = updatedAt; return this; }

        public Address build() {
            return new Address(this);
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Address{" +
                "userId=" + userId +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", addressType='" + addressType + '\'' +
                '}';
    }
}
