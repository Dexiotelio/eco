package com.ecommerce.demo.repositories;

import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserWriteRepositoryImpl implements UserWriteRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public  UserWriteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Result<Void> create(User user) {
        String sql = "INSERT INTO \"Users\" (firstname, lastname, username, age, email, phones, " +
                "password, gender, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return Try.of(() -> {
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getUserName(),
                    user.getAge(), user.getEmail(), user.getPhones(), user.getPassword(),
                    user.getGender(), user.getRole());
            return Result.success();
        })
        .getOrElseGet(e -> Result.failure(DatabaseError.INSERTION_ERROR.getMessage() + ": " + e.getMessage()));
    }

    @Override
    public void update(User user) {}

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM \"Users\" WHERE id = ?";

        Try.of(() -> {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                return Result.failure("User not found.");
            }
            return Result.success("User deleted");
        }).getOrElseGet(e -> Result.failure("Error deleting user: " + e.getMessage()));
    }
}
