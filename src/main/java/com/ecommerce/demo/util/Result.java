package com.ecommerce.demo.util;

public class Result<T> {
    private final T value;
    private final boolean isSuccees;
    private final String error;

    private Result(T value, boolean isSuccees, String error ) {
        this.value = value;
        this.isSuccees = isSuccees;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(value, true, null);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(null, false, error);
    }
}
