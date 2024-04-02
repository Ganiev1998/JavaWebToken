package com.example.JavaWebToken.exeptions;

public class UserNotFoundEx extends RuntimeException {
    public UserNotFoundEx(String message) {
        super(message);
    }
}
