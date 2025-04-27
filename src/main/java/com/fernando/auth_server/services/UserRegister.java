package com.fernando.auth_server.services;

import com.fernando.auth_server.dto.CreateRequestUserDTO;
import com.fernando.auth_server.dto.UserDTO;

public interface UserRegister {
    UserDTO registerUser(CreateRequestUserDTO createUserDTO);
}
