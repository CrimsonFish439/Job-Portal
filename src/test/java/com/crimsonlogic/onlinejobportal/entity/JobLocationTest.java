package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JobLocationTest {

    private JobLocation jobLocation;
    private Job mockJob;
    private Location mockLocation;

    @BeforeEach
    void setUp() {
        jobLocation = new JobLocation();
        mockJob = mock(Job.class);
        mockLocation = mock(Location.class);

        jobLocation.setId(1L);
        jobLocation.setJob(mockJob);
        jobLocation.setLocation(mockLocation);
    }

    @Test
    void testGetters() {
        assertEquals(1L, jobLocation.getId());
        assertEquals(mockJob, jobLocation.getJob());
        assertEquals(mockLocation, jobLocation.getLocation());
    }

    @Test
    void testSetters() {
        Job newJob = mock(Job.class);
        Location newLocation = mock(Location.class);
        jobLocation.setJob(newJob);
        jobLocation.setLocation(newLocation);

        assertEquals(newJob, jobLocation.getJob());
        assertEquals(newLocation, jobLocation.getLocation());
    }
}
