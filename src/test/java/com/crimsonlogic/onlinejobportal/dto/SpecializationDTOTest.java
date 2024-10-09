package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecializationDTOTest {

    private SpecializationDTO specializationDTO;

    @BeforeEach
    void setUp() {
        specializationDTO = new SpecializationDTO();
        specializationDTO.setSpecializationId(1L);
        specializationDTO.setSpecializationName("Software Engineering");
    }

    @Test
    void testGetters() {
        assertEquals(1L, specializationDTO.getSpecializationId());
        assertEquals("Software Engineering", specializationDTO.getSpecializationName());
    }

    @Test
    void testSetters() {
        specializationDTO.setSpecializationName("Data Science");
        assertEquals("Data Science", specializationDTO.getSpecializationName());
    }
}
