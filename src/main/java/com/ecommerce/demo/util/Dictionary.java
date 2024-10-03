package com.ecommerce.demo.util;

import io.vavr.control.Either;

import java.util.*;
import java.util.function.Predicate;

// Generic class that provides a mechanism for validation rules
public class Dictionary<T> {
    // Map to hold predicates and their corresponding error messages
    private final Map<Predicate<T>, String> actions = new HashMap<>();

    // Method to add a validation rule
    public void addValidationRule(Predicate<T> predicate, String errors) {
        actions.put(predicate, errors); // Store the predicate and error message in the map
    }

    // Method to validate an item against the added rules
    public Either<Set<String>, T> validate(T item) {
        Set<String> errors = new HashSet<>(); // Set to collect any validation errors
        // Iterate through each validation rule
        for (Map.Entry<Predicate<T>, String> entry : actions.entrySet()) {
            // Test the item against the predicate
            if (!entry.getKey().test(item)) {
                errors.add(entry.getValue()); // Add error message if validation fails
            }
        }
        // Return the item if there are no errors, or the errors if validation fails
        return errors.isEmpty() ? Either.right(item) : Either.left(errors);
    }
}
