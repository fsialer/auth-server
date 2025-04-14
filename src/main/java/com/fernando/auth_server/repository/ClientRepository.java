package com.fernando.auth_server.repository;

import com.fernando.auth_server.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByClientId(String clientId);
}
