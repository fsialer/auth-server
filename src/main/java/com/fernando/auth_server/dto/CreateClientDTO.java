package com.fernando.auth_server.dto;

import lombok.*;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDTO {
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<String> authenticationMethods;
    private Set<String> grantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private Set<String> postLogoutRedirectUris;
    private boolean requireProofKe;
}
