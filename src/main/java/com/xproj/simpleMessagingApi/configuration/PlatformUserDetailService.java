package com.xproj.simpleMessagingApi.configuration;

import com.xproj.simpleMessagingApi.model.PlatformUsers;
import com.xproj.simpleMessagingApi.repository.PlatformUsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlatformUserDetailService implements UserDetailsService {
     private final PlatformUsersRepository platformUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PlatformUsers platformUsers = platformUsersRepository
                .findByEmail(username).orElseThrow(()->(new UsernameNotFoundException("username not found")));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(platformUsers.getRole()));
        return new User(platformUsers.getEmail(), platformUsers.getPassword(), authorities);
    }
}
