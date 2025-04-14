package com.fernando.auth_server.federated;

import com.fernando.auth_server.entity.GoogleUserEntity;
import com.fernando.auth_server.repository.GoogleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@RequiredArgsConstructor
public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {
    private final GoogleUserRepository googleUserRepository;

    @Override
    public void accept(OAuth2User user) {
        // Capture user in a local data store on first authentication
        if (!this.googleUserRepository.findByEmail(user.getName()).isPresent()) {
            GoogleUserEntity googleUser=GoogleUserEntity.fromOauth2User(user);
            System.out.println("Saving first-time user: name=" + user.getName() + ", claims=" + user.getAttributes() + ", authorities=" + user.getAuthorities());
            this.googleUserRepository.save(googleUser);
        }else{
            System.out.printf("Bienvenido: "+user.getAttributes().get("give_name"));
        }
    }
}
