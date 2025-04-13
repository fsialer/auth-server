package com.fernando.auth_server.repository;

import com.fernando.auth_server.entity.GoogleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoogleUserRepository  extends JpaRepository<GoogleUserEntity, Integer> {
    Optional<GoogleUserEntity> findByEmail(String email);
}
