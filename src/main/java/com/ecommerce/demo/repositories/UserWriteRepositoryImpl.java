package com.ecommerce.demo.repositories;

import com.ecommerce.demo.enums.Role;
import com.ecommerce.demo.model.User;
import com.ecommerce.demo.enums.DatabaseError;
import com.ecommerce.demo.repositories.interfaces.UserWriteRepository;
import com.ecommerce.demo.util.Result;
import io.vavr.control.Try;
import org.postgresql.util.PGobject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserWriteRepositoryImpl implements UserWriteRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserWriteRepositoryImpl.class);

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

        // Attempt to insert a new user into the database
        return Try.of(() -> {
                    // Create a PostgreSQL object for the phones array
                    PGobject phonesObj = new PGobject();
                    phonesObj.setType("text[]");
                    phonesObj.setValue("{" + String.join(",", user.getPhones()) + "}");

                    String roles = String.join(", ", user.getRole().stream()
                            .map(Role::getValue)
                            .toArray(String[]::new)
                            );

                    // Execute the query and retrieve the generated user ID
                    Long userId = jdbcTemplate.queryForObject(sql, new Object[]{
                            user.getFirstName(), user.getLastName(), user.getUserName(),
                            user.getAge(), user.getEmail(), phonesObj, user.getPassword(),
                            user.getGender().getValue().toLowerCase(), roles
                    }, Long.class);
                    return Result.success(userId); // Log user creation
                })
        .getOrElseGet(e -> Result.failure(DatabaseError.INSERTION_ERROR.getMessage() + ": " + e.getMessage()));
    }

    @Override
    public void update(User user) {}

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM \"Users\" WHERE id = ?";

        // Attempt to delete the user with the specified ID
        Try.of(() -> {
            int rowsAffected = jdbcTemplate.update(sql, id);
            if (rowsAffected == 0) {
                logger.warn("No user found with ID: {}", id); // Log if no user was found
                return Result.failure("User not found.");
            }
            logger.info("User with ID: {} deleted successfully", id); // Log successful deletion
            return Result.success("User deleted");
        }).getOrElseGet(e -> {
            logger.error("Error deleting user with ID {}: {}", id, e.getMessage()); // Log the error
            return Result.failure("Error deleting user: " + e.getMessage());
        });
    }

}
