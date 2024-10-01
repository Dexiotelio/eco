package com.ecommerce.demo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Dictionary<T> {
    private final Map<Predicate<T>, Consumer<T>> actions = new HashMap<>();

    public void put(Predicate<T> predicate, Consumer<T> consumer) {
        actions.put(predicate, consumer);
    }

    public void evaluate(T item) {
        for (Map.Entry<Predicate<T>, Consumer<T>> entry : actions.entrySet()) {
            if (entry.getKey().test(item)) {
                entry.getValue().accept(item);
            }
        }
    }
}
