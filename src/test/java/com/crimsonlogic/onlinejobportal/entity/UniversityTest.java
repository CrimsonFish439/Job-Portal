package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UniversityTest {

    private University university;

    @BeforeEach
    void setUp() {
        university = new University();
        university.setUniversityId(1L);
        university.setUniversityName("Harvard University");
    }

    @Test
    void testGetters() {
        assertEquals(1L, university.getUniversityId());
        assertEquals("Harvard University", university.getUniversityName());
    }

    @Test
    void testSetters() {
        university.setUniversityId(2L);
        university.setUniversityName("Stanford University");

        assertEquals(2L, university.getUniversityId());
        assertEquals("Stanford University", university.getUniversityName());
    }
}
