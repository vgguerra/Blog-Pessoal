package com.blogpessoal.models.enums;

public enum Roles {

    ADMIN("admin"),
    PUBLISHER("publisher"),
    USER("user");

    private String role;

    private Roles(String role) {
        this.role = role;
    }

}
