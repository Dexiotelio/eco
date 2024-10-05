package com.ecommerce.demo.repositories.interfaces;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.util.Result;

public interface AddressWriteRepository {
    Result<Void> create(Long userId, AddressRequest address);
    Result<Address> findById(Long id);
    Result<Void> delete(Long id);
}
