package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UniversityDTOTest {

    private UniversityDTO universityDTO;

    @BeforeEach
    void setUp() {
        universityDTO = new UniversityDTO();
        universityDTO.setUniversityId(1L);
        universityDTO.setUniversityName("Harvard University");
    }

    @Test
    void testGetters() {
        assertEquals(1L, universityDTO.getUniversityId());
        assertEquals("Harvard University", universityDTO.getUniversityName());
    }

    @Test
    void testSetters() {
        universityDTO.setUniversityName("Stanford University");
        assertEquals("Stanford University", universityDTO.getUniversityName());
    }
}
