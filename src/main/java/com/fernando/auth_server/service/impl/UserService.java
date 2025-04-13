package com.fernando.auth_server.service.impl;

import com.fernando.auth_server.dto.CreateUserDTO;
import com.fernando.auth_server.dto.UserDTO;
import com.fernando.auth_server.entity.RolEntity;
import com.fernando.auth_server.entity.UserEntity;
import com.fernando.auth_server.enums.RoleName;
import com.fernando.auth_server.mapper.UserMapper;
import com.fernando.auth_server.repository.RolRepository;
import com.fernando.auth_server.repository.UserRepository;
import com.fernando.auth_server.service.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserCreate {
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        if (userRepository.findByUsername(createUserDTO.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        Set<RolEntity> roles = new HashSet<>();
        createUserDTO.getRoles().forEach(r -> {
            RolEntity role = rolRepository.findByRole(RoleName.valueOf(r))
                    .orElseThrow(()-> new RuntimeException("role not found"));
            roles.add(role);
        });
        UserEntity userEntity=UserEntity
                .builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .roles(roles)
                .build();
        return UserMapper.userToUserDTO(userRepository.save(userEntity));
    }
}
