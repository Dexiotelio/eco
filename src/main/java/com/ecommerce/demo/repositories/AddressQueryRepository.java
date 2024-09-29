package com.ecommerce.demo.repositories;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;

public interface AddressQueryRepository {
    Try<Result<Boolean>> exists(AddressRequest addressRequest);
}
