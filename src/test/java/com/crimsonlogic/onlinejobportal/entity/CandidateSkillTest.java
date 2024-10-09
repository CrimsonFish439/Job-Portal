package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandidateSkillTest {

    private CandidateSkill candidateSkill;
    private Candidate mockCandidate;
    private Skill mockSkill;

    @BeforeEach
    void setUp() {
        // Initialize a new CandidateSkill object before each test
        candidateSkill = new CandidateSkill();

        // Mock dependencies (Candidate and Skill)
        mockCandidate = mock(Candidate.class);
        mockSkill = mock(Skill.class);

        // Set properties of the candidateSkill
        candidateSkill.setId(1L);
        candidateSkill.setCandidate(mockCandidate);
        candidateSkill.setSkill(mockSkill);
    }

    @Test
    void testGetters() {
        assertEquals(1L, candidateSkill.getId());
        assertEquals(mockCandidate, candidateSkill.getCandidate());
        assertEquals(mockSkill, candidateSkill.getSkill());
    }

    @Test
    void testSetters() {
        candidateSkill.setId(2L);
        assertEquals(2L, candidateSkill.getId());

        Candidate newCandidate = mock(Candidate.class);
        candidateSkill.setCandidate(newCandidate);
        assertEquals(newCandidate, candidateSkill.getCandidate());

        Skill newSkill = mock(Skill.class);
        candidateSkill.setSkill(newSkill);
        assertEquals(newSkill, candidateSkill.getSkill());
    }
}
