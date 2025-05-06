package com.fernando.auth_server.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserSendDTO(String names, String lastNames, String email, String userId, String sex, LocalDate birth) {}
