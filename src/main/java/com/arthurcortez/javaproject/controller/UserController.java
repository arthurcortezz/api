package com.arthurcortez.javaproject.controller;

import com.arthurcortez.javaproject.entity.UserEntity;
import com.arthurcortez.javaproject.service.UserService;

import jakarta.validation.Valid;

import com.arthurcortez.javaproject.dto.UpdateUserDto;
import com.arthurcortez.javaproject.dto.EditProfileResponseDto;
import com.arthurcortez.javaproject.dto.IncorrectPasswordResponseDto;
import com.arthurcortez.javaproject.dto.UpdatePasswordDto;
import com.arthurcortez.javaproject.dto.UserPaginatedInterfaceDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/list")
    public UserPaginatedInterfaceDto findAllUsers(Pageable pageable) {
        Page<UserEntity> usersPage = service.findAllUsers(pageable);

        UserPaginatedInterfaceDto userPaginatedInterface = new UserPaginatedInterfaceDto();
        userPaginatedInterface.setRows(usersPage.getContent());
        userPaginatedInterface.setCount(usersPage.getTotalElements());

        return userPaginatedInterface;
    }

    @PostMapping("/edit-profile")
    public ResponseEntity<?> editUser(@RequestBody @Valid UpdateUserDto user) {
        UserEntity savedUser = service.editUser(user);
        return ResponseEntity.ok(new EditProfileResponseDto("Usuário", "Usuário editado com sucesso", savedUser));
    }

    @PostMapping("/edit-password")
    public ResponseEntity<?> editPassword(@RequestHeader("Authorization") String token,
            @RequestBody @Valid UpdatePasswordDto passwordDto) {
        try {
            UserEntity savedUser = service.editPassword(token, passwordDto);
            return ResponseEntity
                    .ok(new EditProfileResponseDto("Usuário", "Senha do usuário editada com sucesso", savedUser));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new IncorrectPasswordResponseDto("Senha incorreta", e.getMessage()));
        }
    }

    public class ResponseMessage {
        private String title;
        private String message;

        public ResponseMessage(String title, String message) {
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
