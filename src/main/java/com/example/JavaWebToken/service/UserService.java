package com.example.JavaWebToken.service;

import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.entity.Users;

public interface UserService {
    PrincipleUser getUserById(Long id);
}
