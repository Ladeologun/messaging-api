package com.xproj.simpleMessagingApi.repository;

import com.xproj.simpleMessagingApi.model.PlatformUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformUsersRepository extends JpaRepository<PlatformUsers, Long> {
    @Override
    Optional<PlatformUsers> findById(Long id);

    Optional<PlatformUsers> findByEmail(String email);
}
