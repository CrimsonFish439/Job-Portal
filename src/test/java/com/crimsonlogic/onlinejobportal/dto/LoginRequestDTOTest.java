package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginRequestDTOTest {

    private LoginRequestDTO loginRequestDTO;

    @BeforeEach
    void setUp() {
        loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("test@example.com");
        loginRequestDTO.setPassword("password123");
    }

    @Test
    void testGetters() {
        assertEquals("test@example.com", loginRequestDTO.getEmail());
        assertEquals("password123", loginRequestDTO.getPassword());
    }

    @Test
    void testSetters() {
        loginRequestDTO.setEmail("new@example.com");
        assertEquals("new@example.com", loginRequestDTO.getEmail());
    }
}
