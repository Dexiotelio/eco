package com.ecommerce.demo.util;

import io.vavr.control.Either;

import java.util.*;
import java.util.function.Predicate;

public class Dictionary<T> {
    private final Map<Predicate<T>, String> actions = new HashMap<>();

    public void put(Predicate<T> predicate, String errors) {
        actions.put(predicate, errors);
    }

    public Either<Set<String>, T> evaluate(T item) {
        Set<String> errors = new HashSet<>();
        for (Map.Entry<Predicate<T>, String> entry : actions.entrySet()) {
            if (!entry.getKey().test(item)) {
                errors.add(entry.getValue());
            }
        }
        if (errors.isEmpty()) {
            return Either.right(item);
        } else {
            return Either.left(errors);
        }
    }
}
