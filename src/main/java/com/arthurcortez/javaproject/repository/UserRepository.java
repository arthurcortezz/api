package com.arthurcortez.javaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.arthurcortez.javaproject.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserDetails findByEmail(String email);
}