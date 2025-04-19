package com.fernando.auth_server.services;

import com.fernando.auth_server.dto.CreateUserDTO;
import com.fernando.auth_server.dto.UserDTO;

public interface UserCreate{
    UserDTO createUser(CreateUserDTO createUserDTO);
}
