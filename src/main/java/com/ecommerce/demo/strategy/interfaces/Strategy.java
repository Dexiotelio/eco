package com.ecommerce.demo.strategy.interfaces;

import java.util.Map;

public interface Strategy {
    void execute();
    boolean supports(Object context);
    String getDescription();
    void configure(Map<String, Object> config);
}
