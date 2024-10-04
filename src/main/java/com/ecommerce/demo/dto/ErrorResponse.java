package com.ecommerce.demo.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class ErrorResponse {
    private final String errorCode;
    private final String message;
    private final ErrorDetails details;
    private final LocalDateTime timestamp;

    public ErrorResponse(String errorCode, String message, ErrorDetails details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDetails getDetails() {
        return details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", details=" + details +
                ", timestamp=" + timestamp +
                '}';
    }

    // Clase anidada para detalles del error
    public static class ErrorDetails {
        private final String providedValue;
        private final Set<String> acceptedValues;

        public ErrorDetails(String providedValue, Set<String> acceptedValues) {
            this.providedValue = providedValue;
            this.acceptedValues = acceptedValues;
        }

        public String getProvidedValue() {
            return providedValue;
        }

        public Set<String> getAcceptedValues() {
            return acceptedValues;
        }

        @Override
        public String toString() {
            return "ErrorDetails{" +
                    "providedValue='" + providedValue + '\'' +
                    ", acceptedValues=" + acceptedValues +
                    '}';
        }
    }
}
