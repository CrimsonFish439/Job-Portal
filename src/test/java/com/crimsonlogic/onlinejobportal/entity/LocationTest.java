package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationTest {

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location();
        location.setLocationId("LOC12345");
        location.setLocationName("New York");
    }

    @Test
    void testGetters() {
        assertEquals("LOC12345", location.getLocationId());
        assertEquals("New York", location.getLocationName());
    }

    @Test
    void testSetters() {
        location.setLocationId("LOC67890");
        location.setLocationName("San Francisco");

        assertEquals("LOC67890", location.getLocationId());
        assertEquals("San Francisco", location.getLocationName());
    }
}
