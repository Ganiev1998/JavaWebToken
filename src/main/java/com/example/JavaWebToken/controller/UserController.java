package com.example.JavaWebToken.controller;

import com.example.JavaWebToken.config.JwtService;
import com.example.JavaWebToken.config.PrincipleUser;
import com.example.JavaWebToken.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/admin")
    public Date get(){
        return Date.valueOf("2024-03-27");
    }
    @GetMapping("/user")
    public String get2(@AuthenticationPrincipal PrincipleUser principleUser){
        return "Hello world"+principleUser.getUsername();
    }
}
