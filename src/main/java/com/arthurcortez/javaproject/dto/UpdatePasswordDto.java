package com.arthurcortez.javaproject.dto;

public record UpdatePasswordDto(String confirmPassword, String newPassword, String password) {
}