package com.fernando.auth_server.entity;

import com.fernando.auth_server.dto.CustomOidcUser;
import com.fernando.auth_server.utils.GenerateIdentifier;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "google_users")
public class GoogleUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String givenName;
    private String familyName;
    private String pictureUrl;
    @Column(name = "user_id")
    private String userId;

    public static GoogleUserEntity fromOauth2User(OAuth2User user){
       return GoogleUserEntity.builder()
                .email(user.getName())
                .name(user.getAttributes().get("name").toString())
                .givenName(user.getAttributes().get("given_name").toString())
                //.familyName(user.getAttributes().get("family_name").toString())
                .pictureUrl(user.getAttributes().get("picture").toString())
                .userId(GenerateIdentifier.generateIdentifierUser())
                .build();
    }

    public static GoogleUserEntity fromOauth2User(OidcUser user){
        return GoogleUserEntity.builder()
                .email(user.getName())
                .name(user.getAttributes().get("name").toString())
                .givenName(user.getAttributes().get("given_name").toString())
                //.familyName(user.getAttributes().get("family_name").toString())
                .pictureUrl(user.getAttributes().get("picture").toString())
                .userId(GenerateIdentifier.generateIdentifierUser())
                .build();
    }
}
