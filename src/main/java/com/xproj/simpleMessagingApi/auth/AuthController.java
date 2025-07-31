package com.xproj.simpleMessagingApi.auth;


import com.xproj.simpleMessagingApi.dtos.LoginRequestDto;
import com.xproj.simpleMessagingApi.dtos.RegisterRequestDto;
import com.xproj.simpleMessagingApi.dtos.RegisterResponseDto;
import com.xproj.simpleMessagingApi.utils.ApplicationResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApplicationResponseWrapper<Map<String, String>>> login (@RequestBody LoginRequestDto loginRequestDto) {

        Map<String, String> response = authService.signInUser(loginRequestDto);
        ApplicationResponseWrapper<Map<String, String>> applicationResponseWrapper = new ApplicationResponseWrapper<>("success", response);
        return ResponseEntity.ok(applicationResponseWrapper);
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationResponseWrapper<RegisterResponseDto>> signUpUser (@RequestBody RegisterRequestDto registerRequestDto) {
        RegisterResponseDto response = authService.registerUser(registerRequestDto);
        ApplicationResponseWrapper<RegisterResponseDto> applicationResponseWrapper = new ApplicationResponseWrapper<>("success", response);
        return ResponseEntity.ok(applicationResponseWrapper);
    }
}
