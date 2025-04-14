package com.fernando.auth_server.repository;

import com.fernando.auth_server.entity.RolEntity;
import com.fernando.auth_server.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Integer> {
    Optional<RolEntity> findByRole(RoleName roleName);
}
