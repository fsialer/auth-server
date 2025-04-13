package com.fernando.auth_server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

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

    public static GoogleUserEntity fromOauth2User(OAuth2User user){
       return GoogleUserEntity.builder()
                .email(user.getName())
                .name(user.getAttributes().get("name").toString())
                .givenName(user.getAttributes().get("given_name").toString())
                //.familyName(user.getAttributes().get("family_name").toString())
                .pictureUrl(user.getAttributes().get("picture").toString())
                .build();
    }
}
