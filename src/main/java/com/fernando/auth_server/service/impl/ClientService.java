package com.fernando.auth_server.service.impl;

import com.fernando.auth_server.dto.ClientDTO;
import com.fernando.auth_server.dto.CreateClientDTO;
import com.fernando.auth_server.entity.ClientEntity;
import com.fernando.auth_server.mapper.ClientMapper;
import com.fernando.auth_server.repository.ClientRepository;
import com.fernando.auth_server.service.ClientCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientCreate {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ClientDTO create(CreateClientDTO createClientDTO) {
        ClientEntity clientSave=ClientEntity.builder()
                .clientName(createClientDTO.getClientName())
                .clientId(createClientDTO.getClientId())
                .clientSecret(passwordEncoder.encode(createClientDTO.getClientSecret()))
                .clientIdIssuedAt(new Date().toInstant())
                .authenticationMethods(createClientDTO.getAuthenticationMethods())
                .grantTypes(createClientDTO.getGrantTypes())
                .redirectUris(createClientDTO.getRedirectUris())
                .postLogoutRedirectUris(createClientDTO.getPostLogoutRedirectUris())
                .scopes(createClientDTO.getScopes())
                .build();
        return ClientMapper.clientToClientDTO(clientRepository.save(clientSave));
    }
}
