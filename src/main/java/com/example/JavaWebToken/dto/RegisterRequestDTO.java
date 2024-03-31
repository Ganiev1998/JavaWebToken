package com.example.JavaWebToken.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
}
