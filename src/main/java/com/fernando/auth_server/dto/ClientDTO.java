package com.fernando.auth_server.dto;

import lombok.*;

@Builder
public record ClientDTO(String clientId,String clientName) {}
