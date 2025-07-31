package com.xproj.simpleMessagingApi.dtos;

import com.xproj.simpleMessagingApi.model.PlatformUsers;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponseDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public RegisterResponseDto(PlatformUsers user) {

        this.id = user.getId().toString();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
