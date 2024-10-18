package com.ecommerce.demo.enums;

public enum Role {
    CLIENT("client"),
    ADMIN("admin"),
    VISITOR("visitor");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
