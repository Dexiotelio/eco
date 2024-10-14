package com.ecommerce.demo.dto.request;

import com.ecommerce.demo.enums.Role;
import io.vavr.control.Either;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class LoginRequest {
    @NotBlank(message = "Username is required")
    @Size(max = 30, message = "Username cannot exceed 30 characters")
    @Pattern(regexp = "^\\w+$", message = "Username can only contain alphanumeric characters and underscores")
    private final String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private final String email;

    @NotBlank(message = "Password is required")
    @Size(min = 10, message = "Password must be at least 10 characters long")
    private final String password;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone must be a valid phone number")
    private final Set<String> phones;

    private final String otp;
    private final boolean isSecondFactorRequired;
    private final boolean rememberMe;
    private final boolean rememberDevice;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Last login must be in the format YYYY-MM-DD")
    private final String lastLogin;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Registration date must be in the format YYYY-MM-DD")
    private final String registrationDate;

    private final String preferences;
    private final String sessionId;
    private final Set<Role> roles;

    // Campos para OAuth
    @Pattern(regexp = "^(google|facebook)$", message = "Provider must be either 'google' or 'facebook'")
    private final String provider;
    private final String accessToken;
    private final String recaptchaToken;

    private LoginRequest(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.phones = builder.phones;
        this.otp = builder.otp;
        this.isSecondFactorRequired = builder.isSecondFactorRequired;
        this.rememberMe = builder.rememberMe;
        this.rememberDevice = builder.rememberDevice;
        this.lastLogin = builder.lastLogin;
        this.registrationDate = builder.registrationDate;
        this.preferences = builder.preferences;
        this.sessionId = builder.sessionId;
        this.roles = builder.roles;
        this.provider = builder.provider;
        this.accessToken = builder.accessToken;
        this.recaptchaToken = builder.recaptchaToken;
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private Set<String> phones;
        private String otp;
        private boolean isSecondFactorRequired;
        private boolean rememberMe;
        private boolean rememberDevice;
        private String lastLogin;
        private String registrationDate;
        private String preferences;
        private String sessionId;
        private Set<Role> roles;
        private String provider;
        private String accessToken;
        private String recaptchaToken;

        public Builder username(String username) { this.username = username; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder phones(Set<String> phones) { this.phones = phones; return this; }
        public Builder otp(String otp) { this.otp = otp; return this; }
        public Builder isSecondFactorRequired(boolean isSecondFactorRequired) { this.isSecondFactorRequired = isSecondFactorRequired; return this; }
        public Builder rememberMe(boolean rememberMe) { this.rememberMe = rememberMe; return this; }
        public Builder rememberDevice(boolean rememberDevice) { this.rememberDevice = rememberDevice; return this; }
        public Builder lastLogin(String lastLogin) { this.lastLogin = lastLogin; return this; }
        public Builder registrationDate(String registrationDate) { this.registrationDate = registrationDate; return this; }
        public Builder preferences(String preferences) { this.preferences = preferences; return this; }
        public Builder sessionId(String sessionId) { this.sessionId = sessionId; return this; }
        public Builder roles(Set<Role> roles) { this.roles = roles; return this; }
        public Builder provider(String provider) { this.provider = provider; return this; }
        public Builder accessToken(String accessToken) { this.accessToken = accessToken; return this; }
        public Builder recaptchaToken(String recaptchaToken) { this.recaptchaToken = recaptchaToken; return this; }

        public Either<String, LoginRequest> build() {
            if (username == null || password == null || email == null) {
                return Either.left("Username, email, and password are required");
            }
            return Either.right(new LoginRequest(this));
        }
    }

    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Set<String> getPhones() { return phones; }
    public String getOtp() { return otp; }
    public boolean isSecondFactorRequired() { return isSecondFactorRequired; }
    public boolean isRememberMe() { return rememberMe; }
    public boolean isRememberDevice() { return rememberDevice; }
    public String getLastLogin() { return lastLogin; }
    public String getRegistrationDate() { return registrationDate; }
    public String getPreferences() { return preferences; }
    public String getSessionId() { return sessionId; }
    public Set<Role> getRoles() { return roles; }
    public String getProvider() { return provider; }
    public String getAccessToken() { return accessToken; }
    public String getRecaptchaToken() { return recaptchaToken; }
}
