package com.fernando.auth_server.federated;

import com.fernando.auth_server.dto.CustomOidcUser;
import com.fernando.auth_server.entity.GoogleUserEntity;
import com.fernando.auth_server.mapper.UserMapper;
import com.fernando.auth_server.repository.GoogleUserRepository;
import com.fernando.auth_server.services.impl.ServicesBusSenderService;
import com.fernando.auth_server.utils.GenerateIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class UserRepositoryOidcUserHandler implements Consumer<OidcUser> {
    private final GoogleUserRepository googleUserRepository;
    private final ServicesBusSenderService servicesBusSenderService;

    @Override
    public void accept(OidcUser oidcUser) {
        GoogleUserEntity googleUser=googleUserRepository.findByEmail(oidcUser.getName())
                .orElseGet(()->{
                    GoogleUserEntity newUser = GoogleUserEntity.fromOauth2User(oidcUser);
                    newUser.setUserId(GenerateIdentifier.generateIdentifierUser());
                    GoogleUserEntity userSaved= googleUserRepository.save(newUser);
                    servicesBusSenderService.sendMessage(UserMapper.toUserSendDTO(userSaved));
                    return userSaved;
                });
        CustomOidcUser customOidcUser= new CustomOidcUser(
                oidcUser,
                googleUser.getUserId()
        );

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null) {
            Authentication newAuth = new UsernamePasswordAuthenticationToken(
                    customOidcUser,
                    currentAuth.getCredentials(),
                    customOidcUser.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        }
    }
}
