package com.ecommerce.demo.repositories;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressQueryRepositoryImpl implements AddressQueryRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AddressQueryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Try<Result<Boolean>> exists(AddressRequest addressRequest) {
        String sql = "SELECT EXISTS (SELECT 1 FROM Address " +
                "WHERE street = ? AND street_number = ? AND city = ?" +
                "AND state = ? AND postal_code = ? AND country = ?)";

        return Try.of(() ->
                        Optional.ofNullable(
                                jdbcTemplate.queryForObject(sql, new Object[]{
                                        addressRequest.getStreet(), addressRequest.getStreetNumber(),
                                        addressRequest.getCity(), addressRequest.getState(),
                                        addressRequest.getPostalCode(), addressRequest.getCountry()
                                }, Boolean.class)
                        )
                        .orElse(false)
                )
                .map(Result::success)
                .recover(e -> Result.failure(
                        DatabaseError.QUERY_EXECUTION_ERROR.getMessage() + ": " + e.getMessage()));
    }
}
