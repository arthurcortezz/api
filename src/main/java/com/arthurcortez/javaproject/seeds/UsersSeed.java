package com.arthurcortez.javaproject.seeds;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.arthurcortez.javaproject.entity.UserEntity;
import com.arthurcortez.javaproject.entity.UserRoleEntity;
import com.arthurcortez.javaproject.repository.UserRepository;

@Component
public class UsersSeed implements CommandLineRunner {

    private final UserRepository userRepository;

    public UsersSeed(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            UserEntity administrator = new UserEntity();
            administrator.setName("Administrador");
            administrator.setPassword("$2a$10$E1O1nByZC8KxSGyN43v8C.9MQrY2N4i6Hh//6wM9XTxJrWiHrFc2y");
            administrator.setRole(UserRoleEntity.ADMIN);
            administrator.setEmail("administrador@gmail.com");
            userRepository.save(administrator);
        }
    }
}