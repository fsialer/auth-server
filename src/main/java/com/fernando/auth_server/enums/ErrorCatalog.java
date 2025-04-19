package com.fernando.auth_server.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCatalog {
    USER_EMAIL_EXISTS("AUTH_MS_001", "Email already exists."),
    USER_BAD_PARAMETERS("AUTH_MS_002", "Bad parameters."),
    ROL_NOT_FOUND("AUTH_MS_003", "Ro not found."),
    INTERNAL_SERVER_ERROR("USER_MS_000", "Internal server error.");

    private final String code;
    private final String message;
}
