package com.example.JavaWebToken.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

/**
 * @description: TODO
 * @date: 26 March 2024 $
 * @time: 1:02 AM 00 $
 * @author: Qudratjon Komilov
 */

@Builder
@Getter
public class LoginRequestDTO {
    private String username;
    private String password;
    private String uuid;
}
