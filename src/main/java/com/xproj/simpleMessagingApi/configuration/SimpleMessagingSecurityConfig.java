package com.xproj.simpleMessagingApi.configuration;

import com.xproj.simpleMessagingApi.exceptionHandlers.CustomAccessDeniedHandler;
import com.xproj.simpleMessagingApi.exceptionHandlers.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SimpleMessagingSecurityConfig {

    @Bean
    public SecurityFilterChain handlePlatformSecurity(HttpSecurity http) throws Exception{
        http.
            requiresChannel(rcc->rcc.anyRequest().requiresInsecure()).
            csrf((csrf) -> csrf.disable()).
            authorizeHttpRequests((requests) -> {
                requests.requestMatchers("/contact", "/notices", "/error", "/register").permitAll()
                        .anyRequest().authenticated();
            });
        http.formLogin(fml-> fml.disable());
        http.exceptionHandling(hbc-> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(hge->hge.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
