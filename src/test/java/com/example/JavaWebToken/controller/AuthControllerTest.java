package com.example.JavaWebToken.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {
    @Mock
    MockMvc api;
    @Test
    void login() {
    }

    @Test
    void register() {
    }

    @Test
    void verify() {
    }
}