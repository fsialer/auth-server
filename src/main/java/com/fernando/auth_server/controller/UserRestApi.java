package com.fernando.auth_server.controller;

import com.fernando.auth_server.dto.CreateUserDTO;
import com.fernando.auth_server.services.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserRestApi {
    private final UserCreate userCreate;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreate.createUser(dto));
    }
}
