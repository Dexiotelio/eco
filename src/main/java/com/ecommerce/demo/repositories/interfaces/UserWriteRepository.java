package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.model.User;
import com.ecommerce.demo.util.Result;

public interface UserWriteRepository {
    Result<Long> create(User user);
    void update(User user);
    void delete(Long id);
}
