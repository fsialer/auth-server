package com.fernando.auth_server.dto;

import lombok.*;

@Builder
public record UserDTO(Long id,String username,String userId) {}
