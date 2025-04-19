package com.fernando.auth_server.services;

import com.fernando.auth_server.dto.ClientDTO;
import com.fernando.auth_server.dto.CreateClientDTO;

public interface ClientCreate {
    ClientDTO create(CreateClientDTO createClientDTO);
}
