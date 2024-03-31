package com.example.JavaWebToken.repository;

import com.example.JavaWebToken.config.UserTokenAuth;
import com.example.JavaWebToken.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    Optional<UserToken> findByToken(String token);
}
