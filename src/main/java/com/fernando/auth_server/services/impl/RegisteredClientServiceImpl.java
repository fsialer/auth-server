package com.fernando.auth_server.services.impl;

import com.fernando.auth_server.entity.ClientEntity;
import com.fernando.auth_server.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisteredClientServiceImpl implements RegisteredClientRepository {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        ClientEntity client = clientRepository.findByClientId(id)
                .orElseThrow(()-> new RuntimeException("client not found"));
        return ClientEntity.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        ClientEntity client = clientRepository.findByClientId(clientId)
                .orElseThrow(()-> new RuntimeException("client not found"));
        return ClientEntity.toRegisteredClient(client);
    }
}
