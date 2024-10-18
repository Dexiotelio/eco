package com.ecommerce.demo.strategy;

import com.ecommerce.demo.strategy.interfaces.Strategy;
import com.ecommerce.demo.util.Result;
import io.vavr.collection.List;

public class AuthContext {
    private final List<Strategy> strategies;

    public AuthContext(List<Strategy> strategies) {
        this.strategies = strategies;
    }

    public Result<Void> authenticate(Object context) {
        return strategies.find(
                strategy -> strategy.supports(context))
                .map(strategy -> {
                    strategy.execute();
                    return Result.success();
                })
                .getOrElse(Result.failure("No compatible authentication strategy was found."));
    }
}
