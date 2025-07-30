package com.xproj.simpleMessagingApi.auth;

import com.xproj.simpleMessagingApi.dtos.LoginRequestDto;
import com.xproj.simpleMessagingApi.exceptionHandlers.ApplicationRuntimeException;
import com.xproj.simpleMessagingApi.websocketConfig.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    public Map<String, String> signInUser(LoginRequestDto loginRequestDto) {
        UserDetails userDetails = null;
        try {
            System.out.println(loginRequestDto.getUsername());
            userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getUsername());
        } catch (Exception e) {
            throw new ApplicationRuntimeException(null,"submitted details do not match our records");
        }

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), userDetails.getPassword())) {
            throw new ApplicationRuntimeException(null,"submitted details do not match our records");
        }

        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
            );
            if (authentication.isAuthenticated()) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("role", userDetails.getAuthorities());
                String token = jwtService.generateToken(claims, userDetails);
                return Map.of("token", token);
            }else {
                throw new RuntimeException("Authentication failed");
            }
        } catch (Exception e) {
            throw new ApplicationRuntimeException(null, e.getMessage());
        }
    }
}
