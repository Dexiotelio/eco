package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.User;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;

public interface UserQueryRepository {
    User findById(Long id);
    Try<Result<Boolean>> exists(String email);
    Try<Result<Boolean>> existsUsername(String username);
    Try<Result<User>> findByUsername(String username);
}
