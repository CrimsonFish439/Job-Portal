package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SkillDTOTest {

    private SkillDTO skillDTO;

    @BeforeEach
    void setUp() {
        skillDTO = new SkillDTO("SKL123", "Java");
    }

    @Test
    void testGetters() {
        assertEquals("SKL123", skillDTO.getSkillId());
        assertEquals("Java", skillDTO.getSkillName());
    }

    @Test
    void testSetters() {
        skillDTO.setSkillName("Python");
        assertEquals("Python", skillDTO.getSkillName());
    }
}
