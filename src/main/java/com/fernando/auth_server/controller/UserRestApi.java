package com.fernando.auth_server.controller;

import com.fernando.auth_server.dto.CreateRequestUserDTO;
import com.fernando.auth_server.dto.CreateUserDTO;
import com.fernando.auth_server.dto.UserResponseDTO;
import com.fernando.auth_server.mapper.UserMapper;
import com.fernando.auth_server.services.UserCreate;
import com.fernando.auth_server.services.UserRegister;
import com.fernando.auth_server.services.impl.ServicesBusSenderService;
import jakarta.validation.Valid;
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
    private final UserRegister userRegister;
    private final ServicesBusSenderService servicesBusSenderService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponseDTO(userCreate.createUser(dto)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody CreateRequestUserDTO dto){
        UserResponseDTO userResponseDTO=UserMapper.toUserResponseDTO(userRegister.registerUser(dto));
        servicesBusSenderService.sendMessage(UserMapper.toUserSendDTO(userResponseDTO,dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }
}
