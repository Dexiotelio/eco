package com.ecommerce.demo.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class ErrorResponse {
    private final String errorCode;
    private final Set<String> errorMessage;
    private final LocalDateTime timestamp;

    public ErrorResponse(String errorCode, Set<String> errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Set<String> getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage=" + errorMessage +
                ", timestamp=" + timestamp +
                '}';
    }
}
