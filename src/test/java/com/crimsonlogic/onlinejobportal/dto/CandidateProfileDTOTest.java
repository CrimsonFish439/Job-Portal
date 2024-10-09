package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;

class CandidateProfileDTOTest {

    private CandidateProfileDTO candidateProfileDTO;
    private CandidateDTO candidateDTO;

    @BeforeEach
    void setUp() {
        candidateProfileDTO = new CandidateProfileDTO();
        candidateDTO = new CandidateDTO();
        candidateProfileDTO.setCandidate(candidateDTO);
        candidateProfileDTO.setStatus(ApplicationStatus.APPLIED);
    }

    @Test
    void testGetters() {
        assertEquals(candidateDTO, candidateProfileDTO.getCandidate());
        assertEquals(ApplicationStatus.APPLIED, candidateProfileDTO.getStatus());
    }

    @Test
    void testSetters() {
        candidateProfileDTO.setStatus(ApplicationStatus.SHORTLISTED);
        assertEquals(ApplicationStatus.SHORTLISTED, candidateProfileDTO.getStatus());
    }
}
