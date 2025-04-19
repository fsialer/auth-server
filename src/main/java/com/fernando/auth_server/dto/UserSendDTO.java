package com.fernando.auth_server.dto;

import lombok.Builder;

@Builder
public record UserSendDTO(String names,String lastNames, String email, String userId) {}
