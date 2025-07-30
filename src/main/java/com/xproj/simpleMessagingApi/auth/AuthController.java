package com.xproj.simpleMessagingApi.auth;


import com.xproj.simpleMessagingApi.dtos.LoginRequestDto;
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
    public ResponseEntity<Map<String, String>> login (@RequestBody LoginRequestDto loginRequestDto) {

        Map<String, String> response = authService.signInUser(loginRequestDto);
        System.out.println(response);

//        ApplicationResponseWrapper<Map<String, String>> applicationResponseWrapper = new ApplicationResponseWrapper<>("Login successful", response);
        return ResponseEntity.ok(response);
    }
}
