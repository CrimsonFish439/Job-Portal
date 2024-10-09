package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndustryTest {

    private Industry industry;

    @BeforeEach
    void setUp() {
        // Initialize a new Industry object before each test
        industry = new Industry();

        // Set properties of the industry
        industry.setIndustryId("IND12345");
        industry.setIndustryName("Information Technology");
    }

    @Test
    void testGetters() {
        assertEquals("IND12345", industry.getIndustryId());
        assertEquals("Information Technology", industry.getIndustryName());
    }

    @Test
    void testSetters() {
        industry.setIndustryId("IND67890");
        assertEquals("IND67890", industry.getIndustryId());

        industry.setIndustryName("Finance");
        assertEquals("Finance", industry.getIndustryName());
    }
}
