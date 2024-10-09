package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;

class JobApplicationDTOTest {

    private JobApplicationDTO jobApplicationDTO;

    @BeforeEach
    void setUp() {
        jobApplicationDTO = new JobApplicationDTO();
        jobApplicationDTO.setJobId("JOB12345");
        jobApplicationDTO.setCandidateId("CND12345");
        jobApplicationDTO.setStatus(ApplicationStatus.APPLIED);
    }

    @Test
    void testGetters() {
        assertEquals("JOB12345", jobApplicationDTO.getJobId());
        assertEquals("CND12345", jobApplicationDTO.getCandidateId());
        assertEquals(ApplicationStatus.APPLIED, jobApplicationDTO.getStatus());
    }

    @Test
    void testSetters() {
        jobApplicationDTO.setStatus(ApplicationStatus.SHORTLISTED);
        assertEquals(ApplicationStatus.SHORTLISTED, jobApplicationDTO.getStatus());
    }
}
