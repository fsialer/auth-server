package com.fernando.auth_server.config;

import com.fernando.auth_server.federated.FederatedIdentityAuthenticationSuccessHandler;
import com.fernando.auth_server.federated.UserRepositoryOAuth2UserHandler;
import com.fernando.auth_server.repository.GoogleUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@RequiredArgsConstructor
public class DefaultSecurityConfig {
    private static final String LOGIN_URI="/login";
    private static final String AUTH_URI="/auth/**";
    private static final String ASSET_URI="/assets/**";
    private static final String LOGOUT_URI="http://localhost:4200/logout";
    private static final String CLIENT_URI="/client/**";
    private static final String LOGIN_ERROR_URI="/login?error";
    private final GoogleUserRepository googleUserRepository;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        FederatedIdentityAuthenticationSuccessHandler successHandler = new FederatedIdentityAuthenticationSuccessHandler();
        successHandler.setOAuth2UserHandler(new UserRepositoryOAuth2UserHandler(googleUserRepository));

        http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(AUTH_URI,CLIENT_URI,ASSET_URI,LOGIN_URI).permitAll()
                        .anyRequest().authenticated()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(
                        formLogin->
                                formLogin.loginPage(LOGIN_URI)

                //                        .Customizer.withDefaults()
                )
                .oauth2Login(oauth->oauth
                        .loginPage(LOGIN_URI)
                        .successHandler(successHandler))
                .csrf(csrf->csrf.ignoringRequestMatchers(AUTH_URI,CLIENT_URI))
                .logout(logout->logout.logoutSuccessUrl(LOGOUT_URI));

        return http.build();
    }

}
