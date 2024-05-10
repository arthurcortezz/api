package com.arthurcortez.javaproject.controller;

import com.arthurcortez.javaproject.entity.UserEntity;
import com.arthurcortez.javaproject.dto.LoginDto;
import com.arthurcortez.javaproject.dto.LoginResponseDto;
import com.arthurcortez.javaproject.dto.RegisterDTO;
import com.arthurcortez.javaproject.repository.UserRepository;
import com.arthurcortez.javaproject.security.TokenService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDto(token, auth));
        } catch (AuthenticationException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Credenciais inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @GetMapping("/signin-token")
    public ResponseEntity<?> loginUsingToken(@RequestHeader("Authorization") String token) {
        try {
            var newToken = token.replace("Bearer", "").trim();

            String email = tokenService.validateToken(newToken);

            UserDetails user = userRepository.findByEmail(email);

            if (user != null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                        user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                return ResponseEntity.ok(authentication.getPrincipal());
            } else {
                throw new BadCredentialsException("Usuário não encontrado");
            }
        } catch (AuthenticationException ex) {
            ErrorResponse errorResponse = new ErrorResponse("Credenciais inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserEntity newUser = new UserEntity(data.name(), data.email(), encryptedPassword, data.role());

        this.userRepository.save(newUser);

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token, auth));
    }

    class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
