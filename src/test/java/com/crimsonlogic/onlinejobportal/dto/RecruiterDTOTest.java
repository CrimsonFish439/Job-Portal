package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.EmployeeRange;

class RecruiterDTOTest {

    private RecruiterDTO recruiterDTO;

    @BeforeEach
    void setUp() {
        recruiterDTO = new RecruiterDTO();
        recruiterDTO.setFullName("John Doe");
        recruiterDTO.setOfficialEmail("john.doe@example.com");
        recruiterDTO.setPassword("password123");
        recruiterDTO.setCompanyName("Tech Solutions");
        recruiterDTO.setEmployeeRange(EmployeeRange.RANGE_100_250);
    }

    @Test
    void testGetters() {
        assertEquals("John Doe", recruiterDTO.getFullName());
        assertEquals("john.doe@example.com", recruiterDTO.getOfficialEmail());
        assertEquals("password123", recruiterDTO.getPassword());
        assertEquals("Tech Solutions", recruiterDTO.getCompanyName());
        assertEquals(EmployeeRange.RANGE_100_250, recruiterDTO.getEmployeeRange());
    }

    @Test
    void testSetters() {
        recruiterDTO.setCompanyName("New Tech Solutions");
        assertEquals("New Tech Solutions", recruiterDTO.getCompanyName());
    }
}
