package com.fernando.auth_server.mapper;

import com.fernando.auth_server.dto.UserDTO;
import com.fernando.auth_server.entity.UserEntity;

public class UserMapper {
    public static UserDTO userToUserDTO(UserEntity user){
        return UserDTO.builder()
                .username(user.getUsername())
                .build();
    }
}
