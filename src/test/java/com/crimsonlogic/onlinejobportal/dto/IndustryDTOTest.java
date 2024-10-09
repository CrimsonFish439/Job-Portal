package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndustryDTOTest {

    private IndustryDTO industryDTO;

    @BeforeEach
    void setUp() {
        industryDTO = new IndustryDTO();
        industryDTO.setIndustryId(1L);
        industryDTO.setIndustryName("Information Technology");
    }

    @Test
    void testGetters() {
        assertEquals(1L, industryDTO.getIndustryId());
        assertEquals("Information Technology", industryDTO.getIndustryName());
    }

    @Test
    void testSetters() {
        industryDTO.setIndustryName("Finance");
        assertEquals("Finance", industryDTO.getIndustryName());
    }
}
