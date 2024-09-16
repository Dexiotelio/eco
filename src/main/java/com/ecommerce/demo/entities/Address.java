package com.ecommerce.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    @NotBlank(message = "Street is required")
    @Size(max = 100, message = "Street cannot exceed 100 characters")
    private String street;

    @Column(name = "street_number", nullable = false)
    @NotBlank(message = "Street number is required")
    @Size(max = 10, message = "Street number cannot exceed 10 characters")
    private String streetNumber;

    @Column(name = "apartment_number")
    @Size(max = 10, message = "Apartment number cannot exceed 10 characters")
    private String apartmentNumber;

    @Column(nullable = false)
    @NotBlank(message = "Neighborhood is required")
    @Size(max = 50, message = "Neighborhood cannot exceed 50 characters")
    private String neighborhood;

    @Column(nullable = false)
    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State cannot exceed 50 characters")
    private String state;

    @Column(name = "postal_code", nullable = false)
    @NotBlank(message = "Postal code is required")
    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    private String postalCode;

    @Column(nullable = false)
    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country cannot exceed 50 characters")
    private String country;

    @Column(nullable = false)
    @NotBlank(message = "Phone is required")
    @Size(max = 20, message = "Phone cannot exceed 20 characters")
    private String phone;

    @Column(name = "address_type", nullable = false)
    @NotBlank(message = "Address type is required")
    @Size(max = 20, message = "Address type cannot exceed 20 characters")
    private String addressType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public @NotBlank(message = "Street is required") @Size(max = 100, message = "Street cannot exceed 100 characters") String getStreet() {
        return street;
    }

    public void setStreet(@NotBlank(message = "Street is required") @Size(max = 100, message = "Street cannot exceed 100 characters") String street) {
        this.street = street;
    }

    public @NotBlank(message = "Street number is required") @Size(max = 10, message = "Street number cannot exceed 10 characters") String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(@NotBlank(message = "Street number is required") @Size(max = 10, message = "Street number cannot exceed 10 characters") String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public @Size(max = 10, message = "Apartment number cannot exceed 10 characters") String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(@Size(max = 10, message = "Apartment number cannot exceed 10 characters") String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public @NotBlank(message = "Neighborhood is required") @Size(max = 50, message = "Neighborhood cannot exceed 50 characters") String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(@NotBlank(message = "Neighborhood is required") @Size(max = 50, message = "Neighborhood cannot exceed 50 characters") String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public @NotBlank(message = "City is required") @Size(max = 50, message = "City cannot exceed 50 characters") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City is required") @Size(max = 50, message = "City cannot exceed 50 characters") String city) {
        this.city = city;
    }

    public @NotBlank(message = "State is required") @Size(max = 50, message = "State cannot exceed 50 characters") String getState() {
        return state;
    }

    public void setState(@NotBlank(message = "State is required") @Size(max = 50, message = "State cannot exceed 50 characters") String state) {
        this.state = state;
    }

    public @NotBlank(message = "Postal code is required") @Size(max = 20, message = "Postal code cannot exceed 20 characters") String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(@NotBlank(message = "Postal code is required") @Size(max = 20, message = "Postal code cannot exceed 20 characters") String postalCode) {
        this.postalCode = postalCode;
    }

    public @NotBlank(message = "Country is required") @Size(max = 50, message = "Country cannot exceed 50 characters") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Country is required") @Size(max = 50, message = "Country cannot exceed 50 characters") String country) {
        this.country = country;
    }

    public @NotBlank(message = "Phone is required") @Size(max = 20, message = "Phone cannot exceed 20 characters") String getPhone() {
        return phone;
    }

    public void setPhone(@NotBlank(message = "Phone is required") @Size(max = 20, message = "Phone cannot exceed 20 characters") String phone) {
        this.phone = phone;
    }

    public @NotBlank(message = "Address type is required") @Size(max = 20, message = "Address type cannot exceed 20 characters") String getAddressType() {
        return addressType;
    }

    public void setAddressType(@NotBlank(message = "Address type is required") @Size(max = 20, message = "Address type cannot exceed 20 characters") String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", client=" + client +
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
