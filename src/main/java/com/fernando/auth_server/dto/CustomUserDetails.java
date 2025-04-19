package com.fernando.auth_server.dto;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getUserIdRandom();
}
