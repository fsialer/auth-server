package com.fernando.auth_server.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomOidcUser implements OidcUser {

    private final OidcUser delegate;
    private final String userId;

    public CustomOidcUser(OidcUser delegate, String userId) {
        this.delegate = delegate;
        this.userId = userId;
    }

    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>(delegate.getAttributes());
        attributes.put("user_id", userId);
        return attributes;
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return delegate.getAuthorities(); }
    @Override public String getName() { return delegate.getName(); }
    @Override public OidcIdToken getIdToken() { return delegate.getIdToken(); }
    @Override public OidcUserInfo getUserInfo() { return delegate.getUserInfo(); }
    @Override public Map<String, Object> getClaims() {
        Map<String, Object> claims = new HashMap<>(delegate.getClaims());
        claims.put("user_id", userId);
        return claims;
    }
}
