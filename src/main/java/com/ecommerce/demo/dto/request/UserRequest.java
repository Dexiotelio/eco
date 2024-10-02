package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.Gender;
import com.ecommerce.demo.enums.Role;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.util.Set;

public class UserRequest {
    @NotNull(message = "Id is required")
    private final Long id;

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
    @Size(min = 10, message = "Password must be at least 10 characters long")
    private final String password;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be greater than 18")
    private final Integer age;

    @NotNull(message = "Gender is required")
    private final Gender gender;

    @NotNull(message = "Phones are required")
    private final Set<String> phones;

    @NotNull(message = "Role is required")
    private final Role role;

    @NotNull(message = "Address is required")
    private final Set<AddressRequest> addresses;

    @JsonCreator
    public UserRequest(
            @JsonProperty("id") Long id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("userName") String userName,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("age") Integer age,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("phones") Set<String> phones,
            @JsonProperty("role") Role role,
            @JsonProperty("addresses") Set<AddressRequest> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.phones = phones;
        this.role = role;
        this.addresses = addresses;
    }


    public UserRequest(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.age = builder.age;
        this.gender = builder.gender;
        this.phones = builder.phones;
        this.role = builder.role;
        this.addresses = builder.addresses;
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private String password;
        private Integer age;
        private Gender gender;
        private Set<String> phones;
        private Role role;
        private Set<AddressRequest> addresses;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName) { this.lastName = lastName; return this; }
        public Builder userName(String userName) { this.userName = userName; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder age(Integer age) { this.age = age; return this; }
        public Builder gender(Gender gender) { this.gender = gender; return this; }
        public Builder phones(Set<String> phones) { this.phones = phones; return this; }
        public Builder role(Role role) { this.role = role; return this; }
        public Builder addresses(Set<AddressRequest> addresses) { this.addresses = addresses; return this; }

        public UserRequest build() {
            return new UserRequest(this);
        }
    }

    public Long getId() {
        return id;
    }

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

    public Set<String> getPhones() {
        return phones;
    }

    public Role getRole() { return role; }

    public Set<AddressRequest> getAddress() { return addresses; }

    @Override
    public String toString() {
        return "UserRequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phones=" + phones +
                ", role=" + role +
                ", address=" + addresses +
                '}';
    }
}
