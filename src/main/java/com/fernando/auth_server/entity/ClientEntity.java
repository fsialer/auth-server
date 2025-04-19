package com.fernando.auth_server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "oauth2_client_authentication_method", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "authentication_method")
    private Set<String> authenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "oauth2_client_grants", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "grant_type")
    private Set<String> grantTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> redirectUris;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> postLogoutRedirectUris;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scopes;

    private ClientSettings clientSettings;
    private TokenSettings tokenSettings;
    private boolean requireProofKey;

    public static RegisteredClient toRegisteredClient(ClientEntity client){
        RegisteredClient.Builder builder = RegisteredClient.withId(client.getClientId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(am -> am.addAll(
                        client.getAuthenticationMethods().stream()
                                .map(ClientAuthenticationMethod::new)
                                .collect(Collectors.toSet())
                ))
                .authorizationGrantTypes(agt -> agt.addAll(
                        client.getGrantTypes().stream()
                                .map(AuthorizationGrantType::new)
                                .collect(Collectors.toSet())
                ))
                .redirectUris(ru -> ru.addAll(client.getRedirectUris()))
                .scopes(sc -> sc.addAll(client.getScopes()))
                .clientSettings(ClientSettings
                        .builder().requireProofKey(client.isRequireProofKey())
                        .requireAuthorizationConsent(true)
                        .build());
        return builder.build();
    }
}
