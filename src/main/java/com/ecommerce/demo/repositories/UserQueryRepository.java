package com.ecommerce.demo.repositories;

import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;

public interface UserQueryRepository {
    User findById(Long id);
    Try<Result<Boolean>> exists(Long id);
}
