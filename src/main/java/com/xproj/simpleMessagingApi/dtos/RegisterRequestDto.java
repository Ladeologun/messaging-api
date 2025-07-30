package com.xproj.simpleMessagingApi.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequestDto {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
