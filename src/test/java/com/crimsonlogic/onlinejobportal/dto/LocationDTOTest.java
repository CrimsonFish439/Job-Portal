package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationDTOTest {

    private LocationDTO locationDTO;

    @BeforeEach
    void setUp() {
        locationDTO = new LocationDTO();
        locationDTO.setLocationId("LOC12345");
        locationDTO.setLocationName("New York");
    }

    @Test
    void testGetters() {
        assertEquals("LOC12345", locationDTO.getLocationId());
        assertEquals("New York", locationDTO.getLocationName());
    }

    @Test
    void testSetters() {
        locationDTO.setLocationName("San Francisco");
        assertEquals("San Francisco", locationDTO.getLocationName());
    }
}
