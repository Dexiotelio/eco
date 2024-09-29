package com.ecommerce.demo.repositories;

import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.util.Result;

public interface AddressWriteRepository {
    Result<Void> create(Address address);
    Result<Address> findById(Long id);
    Result<Void> delete(Long id);
}
