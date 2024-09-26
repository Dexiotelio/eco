package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Arrays;

public class UserRequest implements Serializable {

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private final String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private final String lastName;

    @NotBlank(message = "Username is required")
    @Size(max = 30, message = "Username cannot exceed 30 characters")
    @Pattern(regexp = "^\\w+$", message = "Username can only contain alphanumeric characters and underscores")
    private final String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private final String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private final String password;

    @NotNull(message = "Age is required")
    @Min(value = 19, message = "Age must be greater than 18")
    private final Integer age;

    @NotNull(message = "Gender is required")
    private final Gender gender;

    @NotNull(message = "Phones are required")
    private final String[] phones;

    @NotNull(message = "Role is required")
    private final Role role;

    // Constructor
    public UserRequest(String firstName, String lastName, String userName, String email,
                       String password, Integer age, Gender gender, String[] phones, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phones = phones;
        this.role = role;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String[] getPhones() {
        return phones;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phones=" + Arrays.toString(phones) +
                ", role=" + role +
                '}';
    }
}
