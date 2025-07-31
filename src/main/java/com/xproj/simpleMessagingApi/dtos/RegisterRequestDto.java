package com.xproj.simpleMessagingApi.dtos;

import com.xproj.simpleMessagingApi.model.PlatformUsers;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String role;


    public PlatformUsers convertToPlatformUser() {
        return PlatformUsers.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .build();
    }
}
