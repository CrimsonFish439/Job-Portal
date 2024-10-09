package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;

class JobApplicationTest {

    private JobApplication jobApplication;
    private Candidate mockCandidate;
    private Job mockJob;

    @BeforeEach
    void setUp() {
        jobApplication = new JobApplication();
        mockCandidate = mock(Candidate.class);
        mockJob = mock(Job.class);

        jobApplication.setApplicationId("APP12345");
        jobApplication.setCandidate(mockCandidate);
        jobApplication.setJob(mockJob);
        jobApplication.setStatus(ApplicationStatus.APPLIED);
    }

    @Test
    void testGetters() {
        assertEquals("APP12345", jobApplication.getApplicationId());
        assertEquals(mockCandidate, jobApplication.getCandidate());
        assertEquals(mockJob, jobApplication.getJob());
        assertEquals(ApplicationStatus.APPLIED, jobApplication.getStatus());
    }

    @Test
    void testSetters() {
        Candidate newCandidate = mock(Candidate.class);
        Job newJob = mock(Job.class);
        jobApplication.setCandidate(newCandidate);
        jobApplication.setJob(newJob);
        jobApplication.setStatus(ApplicationStatus.SHORTLISTED);

        assertEquals(newCandidate, jobApplication.getCandidate());
        assertEquals(newJob, jobApplication.getJob());
        assertEquals(ApplicationStatus.SHORTLISTED, jobApplication.getStatus());
    }
}
