package com.ecommerce.demo.strategy;

import com.ecommerce.demo.strategy.interfaces.Strategy;

import java.util.Map;

public class TokenAuthentication implements Strategy {
    @Override
    public void execute() {
        // validate token
    }

    @Override
    public boolean supports(Object context) {
        return false;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void configure(Map<String, Object> config) {

    }
}
