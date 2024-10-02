package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.dto.response.AddressResponse;
import com.ecommerce.demo.entities.Address;
import com.ecommerce.demo.enums.AddressErrorCode;
import com.ecommerce.demo.repositories.AddressQueryRepositoryImpl;
import com.ecommerce.demo.repositories.AddressWriteRepositoryImpl;
import com.ecommerce.demo.util.AddressValidation;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AddressWriteServicesImpl implements AddressWriteServices {
    private final AddressWriteRepositoryImpl addressWriteRepository;
    private final AddressQueryRepositoryImpl addressQueryRepository;

    @Autowired
    public AddressWriteServicesImpl(AddressWriteRepositoryImpl addressWriteRepository,
                                    AddressQueryRepositoryImpl addressQueryRepository) {
        this.addressWriteRepository = addressWriteRepository;
        this.addressQueryRepository = addressQueryRepository;
    }

    @Override
    @Transactional
    public Result<AddressResponse> create(AddressRequest request) {
        Either<Set<String>, AddressRequest> addressValidation = AddressValidation.validateAddressRequest(request);
        if (addressValidation.isLeft()) {
            return Result.failure(addressValidation.getLeft());
        }

        Try<Result<Boolean>> addressExists = addressQueryRepository.exists(request);
        if (addressExists.isFailure()) {
            return Result.failure(addressExists.getCause().getMessage());
        }

        Result<Boolean> addressExistsResult = addressExists.get();
        if (addressExistsResult.isSuccess() && addressExistsResult.getValue()) {
            return Result.failure(AddressErrorCode.ADDRESS_ALREADY_EXISTS.getMessage());
        }

        Address address = Address.toAddress(request);
        Result<Void> addressCreationResult = addressWriteRepository.create(address);
        if (addressCreationResult.isFailure()) {
            return Result.failure(AddressErrorCode.ADDRESS_CREATION_FAILURE
                    + String.join(", ", addressCreationResult.getErrors()));
        }

        AddressResponse response = AddressResponse.toAddressResponse(address);
        return Result.success(response);
    }

    @Override
    public Result<AddressResponse> update(AddressRequest request) {
        return null;
    }

    @Override
    public Result<AddressResponse> delete(AddressRequest request) {
        return null;
    }
}
