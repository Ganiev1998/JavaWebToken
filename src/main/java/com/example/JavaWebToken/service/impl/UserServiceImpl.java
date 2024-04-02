package com.example.JavaWebToken.service.impl;

import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.entity.Users;
import com.example.JavaWebToken.repository.UserRepository;
import com.example.JavaWebToken.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Override
    public PrincipleUser getUserById(Long id) {
        Users users = repository.getReferenceById(id);
        return PrincipleUser.builder()
                .id(users.getId())
                .username(users.getUsername())
                .password(users.getPassword())
                .roles(users.getRole())
                .build();
    }
}
