package com.fernando.auth_server.mapper;

import com.fernando.auth_server.dto.ClientDTO;
import com.fernando.auth_server.entity.ClientEntity;

public class ClientMapper {
    private ClientMapper(){}
    public static ClientDTO clientToClientDTO(ClientEntity client){
        return ClientDTO.builder()
                .clientName(client.getClientName())
                .clientId(client.getClientId())
                .build();
    }
}
