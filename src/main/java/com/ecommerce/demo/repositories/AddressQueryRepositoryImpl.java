package com.ecommerce.demo.repositories;

import com.ecommerce.demo.dto.request.AddressRequest;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
                        Boolean.TRUE.equals(
                                jdbcTemplate.queryForObject(sql, new Object[]{
                                        addressRequest.getStreet(), addressRequest.getStreetNumber(),
                                        addressRequest.getCity(), addressRequest.getState(),
                                        addressRequest.getPostalCode(), addressRequest.getCountry()
                                }, Boolean.class)
                        ))
                .map(Result::success)
                .onFailure(e -> Result.failure("Error checking address existence: " + e.getMessage()));
    }
}
