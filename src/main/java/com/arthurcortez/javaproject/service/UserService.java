package com.arthurcortez.javaproject.service;

import com.arthurcortez.javaproject.dto.UpdateUserDto;
import com.arthurcortez.javaproject.entity.UserEntity;
import com.arthurcortez.javaproject.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<UserEntity> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public UserEntity editUser(UpdateUserDto user) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(user.id());
        if (optionalUserEntity.isPresent()) {
            UserEntity existingUser = optionalUserEntity.get();
            existingUser.setName(user.name());
            existingUser.setEmail(user.email());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id " + user.id());
        }
    }
}