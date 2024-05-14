package com.arthurcortez.javaproject.service;

import com.arthurcortez.javaproject.dto.UpdatePasswordDto;
import com.arthurcortez.javaproject.dto.UpdateUserDto;
import com.arthurcortez.javaproject.entity.UserEntity;
import com.arthurcortez.javaproject.repository.UserRepository;
import com.arthurcortez.javaproject.security.TokenService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

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

    public UserEntity editPassword(String token, UpdatePasswordDto passwordDto) {
        var newToken = token.replace("Bearer", "").trim();
        String email = tokenService.validateToken(newToken);
        UserEntity user = (UserEntity) userRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("Usuário não encontrado com email " + email);
        }

        Optional<UserEntity> optionalUserEntity = userRepository.findById(user.getId());

        if (optionalUserEntity.isPresent()) {
            UserEntity existingUser = optionalUserEntity.get();
            if (passwordEncoder.matches(passwordDto.password(), existingUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(passwordDto.newPassword()));
                return userRepository.save(existingUser);
            } else {
                throw new RuntimeException("Senha antiga incorreta");
            }
        } else {
            throw new RuntimeException("Usuário não encontrado com id " + user.getId());
        }
    }
}