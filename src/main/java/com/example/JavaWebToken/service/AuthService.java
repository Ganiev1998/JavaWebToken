package com.example.JavaWebToken.service;

import com.example.JavaWebToken.dto.LoginRequestDTO;
import com.example.JavaWebToken.dto.LoginResponseDTO;
import com.example.JavaWebToken.dto.RegisterRequestDTO;
import com.example.JavaWebToken.dto.RegisterResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO dto);
    String register(RegisterRequestDTO dto);
    String verify(String uuid);
}
