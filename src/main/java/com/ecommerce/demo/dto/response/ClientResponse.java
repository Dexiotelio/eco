package com.ecommerce.demo.dto.response;

import com.ecommerce.demo.enums.Gender;

import java.time.ZonedDateTime;

public class ClientResponse {
    public static class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String username;
        private String email;
        private Integer age;
        private Gender gender;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder firstname(String firstname) { this.firstname = firstname; return this; }
        public Builder lastname(String lastname) { this.lastname = lastname; return this; }
        public Builder username(String username) { this.username = username; return this;}
        public Builder email(String email) { this.email = email; return this; }
        public Builder age(Integer age) { this.age = age; return this; }
        public Builder gender(Gender gender) { this.gender = gender; return this; }
        public Builder created(ZonedDateTime createdAt) {this.createdAt = createdAt; return this; }
        public Builder update(ZonedDateTime updatedAt) {this.updatedAt = updatedAt; return this; }

        public ClientResponse build() {
            return new ClientResponse(this);
        }
    }

    private final Long id;
    private final String firstname;
    private final String lastname;
    private final String username;
    private final String email;
    private final Integer age;
    private final Gender gender;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public ClientResponse(Builder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.username = builder.username;
        this.email = builder.email;
        this.age = builder.age;
        this.gender = builder.gender;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ClientResponse{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
