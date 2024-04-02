package com.example.JavaWebToken.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
public class UserInfoDTO implements Serializable {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private List<String> role;
}
