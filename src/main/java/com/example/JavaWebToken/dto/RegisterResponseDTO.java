package com.example.JavaWebToken.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RegisterResponseDTO {
    private String token;
    private String username;
    private String uuid;
}
