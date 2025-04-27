package com.fernando.auth_server.mapper;

import com.fernando.auth_server.dto.*;
import com.fernando.auth_server.entity.GoogleUserEntity;
import com.fernando.auth_server.entity.RolEntity;
import com.fernando.auth_server.entity.UserEntity;

import java.util.Set;

public class UserMapper {

    public static UserDTO userToUserDTO(UserEntity user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userId(user.getUserId())
                .build();
    }

    public static UserResponseDTO toUserResponseDTO(UserDTO userDTO){
        return UserResponseDTO.builder()
                .username(userDTO.username())
                .userId(userDTO.userId())
                .build();
    }

    public static UserEntity toUserEntity(CreateRequestUserDTO createRequestUserDTO, Set<RolEntity> roles, String userId){
        return UserEntity.builder()
                .username(createRequestUserDTO.getUsername())
                .password(createRequestUserDTO.getPassword())
                .roles(roles)
                .userId(userId)
                .build();
    }

    public static UserSendDTO toUserSendDTO(UserResponseDTO userResponseDTO, CreateRequestUserDTO createRequestUserDTO){
        return UserSendDTO.builder()
                .names(createRequestUserDTO.getProfile().getNames())
                .lastNames(createRequestUserDTO.getProfile().getLastNames())
                .email(userResponseDTO.username())
                .userId(userResponseDTO.userId())
                .build();
    }

    public static UserSendDTO toUserSendDTO(GoogleUserEntity googleUserEntity){
        return UserSendDTO.builder()
                .names(googleUserEntity.getGivenName())
                .email(googleUserEntity.getName())
                .userId(googleUserEntity.getUserId())
                .build();
    }
}
