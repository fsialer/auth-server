package com.fernando.auth_server.dto;

import lombok.*;

@Builder
public record UserResponseDTO (String username,String userId){}