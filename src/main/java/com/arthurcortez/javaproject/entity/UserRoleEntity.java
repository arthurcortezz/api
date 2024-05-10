package com.arthurcortez.javaproject.entity;

public enum UserRoleEntity {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoleEntity(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}