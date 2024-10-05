package com.ecommerce.demo.dto.request;

import io.vavr.control.Either;

public class LoginRequest {
    private final String username;
    private final String email;
    private final String password;
    private final boolean rememberMe;

    private LoginRequest(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.rememberMe = builder.rememberMe;
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private boolean rememberMe;

        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder rememberMe(boolean rememberMe) { this.rememberMe = rememberMe; return this; }

        public Either<String,LoginRequest> build() {
            if (password == null) {
                return Either.left("Password is required");
            }
                return Either.right(new LoginRequest(this));
        }
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean isRememberMe() { return rememberMe; }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
