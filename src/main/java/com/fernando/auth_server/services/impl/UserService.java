package com.fernando.auth_server.services.impl;

import com.fernando.auth_server.dto.CreateRequestUserDTO;
import com.fernando.auth_server.dto.CreateUserDTO;
import com.fernando.auth_server.dto.UserDTO;
import com.fernando.auth_server.entity.RolEntity;
import com.fernando.auth_server.entity.UserEntity;
import com.fernando.auth_server.enums.RoleName;
import com.fernando.auth_server.exceptions.EmailExistsException;
import com.fernando.auth_server.exceptions.RolNotFoundException;
import com.fernando.auth_server.mapper.UserMapper;
import com.fernando.auth_server.repository.RolRepository;
import com.fernando.auth_server.repository.UserRepository;
import com.fernando.auth_server.services.UserCreate;
import com.fernando.auth_server.services.UserRegister;
import com.fernando.auth_server.utils.GenerateIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserCreate, UserRegister {
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(CreateUserDTO createUserDTO) {
        if (userRepository.findByUsername(createUserDTO.getUsername()).isPresent()) {
            throw new EmailExistsException();
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
                .userId(GenerateIdentifier.generateIdentifierUser())
                .build();

        return UserMapper.userToUserDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO registerUser(CreateRequestUserDTO createUserDTO) {
        if (userRepository.findByUsername(createUserDTO.getUsername()).isPresent()) {
            throw new EmailExistsException();
        }
        Set<RolEntity> roles = new HashSet<>();
        String userId=GenerateIdentifier.generateIdentifierUser();
        RolEntity role = rolRepository.findByRole(RoleName.ROLE_USER)
                .orElseThrow(RolNotFoundException::new);
        roles.add(role);
        createUserDTO.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        return UserMapper.userToUserDTO(userRepository.save(UserMapper.toUserEntity(createUserDTO,roles,userId)));
    }
}
