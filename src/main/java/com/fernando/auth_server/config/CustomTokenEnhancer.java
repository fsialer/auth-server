package com.fernando.auth_server.config;

import com.fernando.auth_server.dto.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomTokenEnhancer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    @Override
    public void customize(JwtEncodingContext context) {
        Authentication principal=context.getPrincipal();

        if(context.getTokenType().getValue().equals("id_token")){
            context.getClaims().claim("token_type","id token");
        }
        if(context.getTokenType().getValue().equals("access_token")){
            context.getClaims().claim("token_type","access token");
            Set<String> roles=principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
            context.getClaims().claim("roles",roles).claim("username",principal.getName());
            // Extraer userId desde tu entidad
            if (principal.getPrincipal() instanceof CustomUserDetails userDetails) {
                context.getClaims().claim("user_id", userDetails.getUserIdRandom());
            }
        }
    }
}
