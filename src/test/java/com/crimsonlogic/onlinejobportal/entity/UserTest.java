package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;
    private Role mockRole;

    @BeforeEach
    void setUp() {
        user = new User();
        mockRole = mock(Role.class);

        user.setUserId("USR12345");
        user.setEmail("user@example.com");
        user.setPassword("password123");
        user.setStatus(true);
        user.setRole(mockRole);
    }

    @Test
    void testGetters() {
        assertEquals("USR12345", user.getUserId());
        assertEquals("user@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertTrue(user.getStatus());
        assertEquals(mockRole, user.getRole());
    }

    @Test
    void testSetters() {
        Role newRole = mock(Role.class);
        user.setUserId("USR67890");
        user.setEmail("newuser@example.com");
        user.setPassword("newpassword");
        user.setStatus(false);
        user.setRole(newRole);

        assertEquals("USR67890", user.getUserId());
        assertEquals("newuser@example.com", user.getEmail());
        assertEquals("newpassword", user.getPassword());
        assertFalse(user.getStatus());
        assertEquals(newRole, user.getRole());
    }
}
