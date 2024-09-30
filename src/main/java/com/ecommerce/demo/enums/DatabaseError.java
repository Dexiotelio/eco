package com.ecommerce.demo.enums;

public enum DatabaseError {
    DATABASE_CONNECTION_FAILED("Failed to connect to the database"),
    DATA_INTEGRITY_VIOLATION("Data integrity violation"),
    CONNECTION_ERROR("Error connecting to the database"),
    QUERY_EXECUTION_ERROR("Error executing the query"),
    INSERTION_ERROR("Error inserting the record"),
    UPDATE_ERROR("Error updating the record"),
    DELETION_ERROR("Error deleting the record"),
    DUPLICATION_ERROR("Error: the record already exists in the database"),
    TRANSACTION_ERROR("Transaction error"),
    SCHEMA_ERROR("Error: column not found in the table");


    private final String message;

    DatabaseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
