package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecruiterTest {

    private Recruiter recruiter;
    private Industry mockIndustry;
    private User mockUser;

    @BeforeEach
    void setUp() {
        recruiter = new Recruiter();
        mockIndustry = mock(Industry.class);
        mockUser = mock(User.class);

        recruiter.setRecruiterId("RCT12345");
        recruiter.setFullName("John Doe");
        recruiter.setCompanyName("Tech Solutions");
        recruiter.setIndustry(mockIndustry);
        recruiter.setUser(mockUser);
    }

    @Test
    void testGetters() {
        assertEquals("RCT12345", recruiter.getRecruiterId());
        assertEquals("John Doe", recruiter.getFullName());
        assertEquals("Tech Solutions", recruiter.getCompanyName());
        assertEquals(mockIndustry, recruiter.getIndustry());
        assertEquals(mockUser, recruiter.getUser());
    }

    @Test
    void testSetters() {
        recruiter.setRecruiterId("RCT67890");
        recruiter.setFullName("Jane Doe");
        recruiter.setCompanyName("Innovate Ltd.");
        Industry newIndustry = mock(Industry.class);
        recruiter.setIndustry(newIndustry);
        User newUser = mock(User.class);
        recruiter.setUser(newUser);

        assertEquals("RCT67890", recruiter.getRecruiterId());
        assertEquals("Jane Doe", recruiter.getFullName());
        assertEquals("Innovate Ltd.", recruiter.getCompanyName());
        assertEquals(newIndustry, recruiter.getIndustry());
        assertEquals(newUser, recruiter.getUser());
    }
}
