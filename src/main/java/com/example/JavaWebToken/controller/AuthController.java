package com.example.JavaWebToken.controller;

import com.example.JavaWebToken.dto.LoginRequestDTO;
import com.example.JavaWebToken.dto.LoginResponseDTO;
import com.example.JavaWebToken.dto.RegisterRequestDTO;
import com.example.JavaWebToken.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl service;
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto){
        return service.login(dto);
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO dto){
        return service.register(dto);
    }
    @PostMapping("verify/{uuid}")
    public String verify(@PathVariable String uuid){
        return service.verify(uuid);
    }
}
