package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.Gender;
import jakarta.validation.constraints.*;

import java.io.Serializable;

public class ClientRequest implements Serializable {
    public static class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String username;
        private String email;
        private String passwond;
        private Integer age;
        private Gender gender;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder firstname(String firstname) { this.firstname = firstname; return this; }
        public Builder lastname(String lastname) { this.lastname = lastname; return this; }
        public Builder username(String username) { this.username = username; return this;}
        public Builder email(String email) { this.email = email; return this; }
        public Builder age(Integer age) { this.age = age; return this; }
        public Builder gender(Gender gender) { this.gender = gender; return this; }

        public ClientRequest build() {
            return new ClientRequest(this);
        }
    }

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstname;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastname;

    @NotBlank(message = "Username is required")
    @Size(max = 30, message = "Username cannot exceed 30 characters")
    @Pattern(regexp = "^\\w+$", message = "Username can only contain alphanumeric characters and underscores")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Age is required")
    @Min(value = 19, message = "Age must be greater than 18")
    private Integer age;

    @NotNull(message = "Gender is required")
    private Gender gender;

    public ClientRequest(Builder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.passwond;
        this.age = builder.age;
        this.gender = builder.gender;
    }

    public @NotBlank(message = "First name is required") @Size(max = 50, message = "First name cannot exceed 50 characters") String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotBlank(message = "First name is required") @Size(max = 50, message = "First name cannot exceed 50 characters") String firstname) {
        this.firstname = firstname;
    }

    public @NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name cannot exceed 50 characters") String getLastname() {
        return lastname;
    }

    public void setLastname(@NotBlank(message = "Last name is required") @Size(max = 50, message = "Last name cannot exceed 50 characters") String lastname) {
        this.lastname = lastname;
    }

    public @NotBlank(message = "Username is required") @Size(max = 30, message = "Username cannot exceed 30 characters") @Pattern(regexp = "^\\w+$", message = "Username can only contain alphanumeric characters and underscores") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required") @Size(max = 30, message = "Username cannot exceed 30 characters") @Pattern(regexp = "^\\w+$", message = "Username can only contain alphanumeric characters and underscores") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
        this.password = password;
    }

    public @NotNull(message = "Age is required") @Min(value = 19, message = "Age must be greater than 18") Integer getAge() {
        return age;
    }

    public void setAge(@NotNull(message = "Age is required") @Min(value = 19, message = "Age must be greater than 18") Integer age) {
        this.age = age;
    }

    public @NotNull(message = "Gender is required") Gender getGender() {
        return gender;
    }

    public void setGender(@NotNull(message = "Gender is required") Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ClientRequest{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
