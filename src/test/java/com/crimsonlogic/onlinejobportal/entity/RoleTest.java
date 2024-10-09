package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoleTest {

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setRoleId("ROLE12345");
        role.setRoleName("ADMIN");
    }

    @Test
    void testGetters() {
        assertEquals("ROLE12345", role.getRoleId());
        assertEquals("ADMIN", role.getRoleName());
    }

    @Test
    void testSetters() {
        role.setRoleId("ROLE67890");
        role.setRoleName("RECRUITER");

        assertEquals("ROLE67890", role.getRoleId());
        assertEquals("RECRUITER", role.getRoleName());
    }
}
