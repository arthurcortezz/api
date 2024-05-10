package com.arthurcortez.javaproject.controller;

import com.arthurcortez.javaproject.entity.UserEntity;
import com.arthurcortez.javaproject.service.UserService;
import com.arthurcortez.javaproject.dto.UserPaginatedInterfaceDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

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
}
