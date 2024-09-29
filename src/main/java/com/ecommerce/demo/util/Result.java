package com.ecommerce.demo.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Result<T> {
    private final T value;
    private final boolean isSuccess;
    private final Set<String> errors;

    private Result(T value, boolean isSuccess, Set<String> errors) {
        this.value = value;
        this.isSuccess = isSuccess;
        this.errors = errors != null ? new HashSet<>(errors) : Collections.emptySet();
    }

    // succes
    public static <T> Result<T> success(T value) {
        return new Result<>(value, true, null);
    }

    public static Result<Void> success() {
        return new Result<>(null, true, null);
    }
    // failure
    public static <T> Result<T> failure(Set<String> errors) {
        return new Result<>(null, false, errors);
    }

    public static  <T> Result<T> failure(String error) {
        return new Result<>(null, false, Collections.singleton(error));
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Set<String> getErrors() {
        return errors;
    }
}
