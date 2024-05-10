package com.arthurcortez.javaproject.dto;

import com.arthurcortez.javaproject.entity.UserRoleEntity;

public record RegisterDTO(String name, String email, String password, UserRoleEntity role) {
}