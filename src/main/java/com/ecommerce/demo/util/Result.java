package com.ecommerce.demo.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Result<T> {
    private final T value;
    private final boolean isSuccess;
    private final Set<String> errors;
    private final String error;

    private Result(T value, boolean isSuccess, Set<String> errors, String error) {
        this.value = value;
        this.isSuccess = isSuccess;
        this.errors = errors != null ? new HashSet<>(errors) : Collections.emptySet();
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, true, null, null);
    }

    public static Result<Void> success() {
        return new Result<>(null, true, null, null);
    }

    public static <T> Result<T> failure(Set<String> errors) {
        return new Result<>(null, false, errors, null);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, false, null, error);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailure() {
        return!isSuccess;
}

    public Set<String> getErrors() {
        return errors;
    }

    public String getError() {
        return error;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "value=" + value +
                ", isSuccess=" + isSuccess +
                ", errors=" + errors +
                '}';
    }
}
