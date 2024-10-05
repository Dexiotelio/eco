package com.ecommerce.demo.repositories;

import com.ecommerce.demo.entities.User;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.repositories.interfaces.UserWriteRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.postgresql.util.PGobject;
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
    public Result<Long> create(User user) {
        String sql = "INSERT INTO \"Users\" (firstname, lastname, username, age, email, phones, " +
                "password, gender, role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";

        return Try.of(() -> {
                    PGobject phonesObj = new PGobject();
                    phonesObj.setType("text[]");
                    phonesObj.setValue("{" + String.join(",", user.getPhones()) + "}");

                    Long userId = jdbcTemplate.queryForObject(sql, new Object[]{
                            user.getFirstName(), user.getLastName(), user.getUserName(),
                            user.getAge(), user.getEmail(), phonesObj, user.getPassword(),
                            user.getGender().getValue().toLowerCase(), user.getRole().getValue().toLowerCase()
                    }, Long.class);
                    return Result.success(userId);
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
