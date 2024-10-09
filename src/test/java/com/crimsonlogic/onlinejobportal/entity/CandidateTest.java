package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.WorkStatus;

class CandidateTest {

    private Candidate candidate;
    private User mockUser;
    private List<CandidateSkill> mockSkills;

    @BeforeEach
    void setUp() {
        // Initialize a new Candidate object before each test
        candidate = new Candidate();

        // Mock dependencies (User and CandidateSkill)
        mockUser = mock(User.class);
        mockSkills = new ArrayList<>();

        // Initialize mock data
        mockSkills.add(mock(CandidateSkill.class));
        mockSkills.add(mock(CandidateSkill.class));

        // Set properties of the candidate
        candidate.setCandidateId("CND12345");
        candidate.setUser(mockUser);
        candidate.setFullName("John Doe");
        candidate.setMobileNumber("9876543210");
        candidate.setGender("Male");
        candidate.setDateOfBirth(LocalDate.of(1990, 1, 1));
        candidate.setWorkStatus(WorkStatus.EXPERIENCED);
        candidate.setCurrentLocation("New York");
        candidate.setHighestQualification("Bachelor's");
        candidate.setCourse("Computer Science");
        candidate.setSpecialization("Software Engineering");
        candidate.setUniversity("Harvard University");
        candidate.setKeySkills(mockSkills);
        candidate.setWorkExperienceYears(5);
        candidate.setAnnualSalary(new BigDecimal("50000.00"));
        candidate.setProfileSummary("Experienced Software Engineer");
        candidate.setResumeUrl("http://example.com/resume.pdf");
        candidate.setProfilePictureUrl("http://example.com/picture.jpg");
    }

    @Test
    void testGetters() {
        assertEquals("CND12345", candidate.getCandidateId());
        assertEquals(mockUser, candidate.getUser());
        assertEquals("John Doe", candidate.getFullName());
        assertEquals("9876543210", candidate.getMobileNumber());
        assertEquals("Male", candidate.getGender());
        assertEquals(LocalDate.of(1990, 1, 1), candidate.getDateOfBirth());
        assertEquals(WorkStatus.EXPERIENCED, candidate.getWorkStatus());
        assertEquals("New York", candidate.getCurrentLocation());
        assertEquals("Bachelor's", candidate.getHighestQualification());
        assertEquals("Computer Science", candidate.getCourse());
        assertEquals("Software Engineering", candidate.getSpecialization());
        assertEquals("Harvard University", candidate.getUniversity());
        assertEquals(mockSkills, candidate.getKeySkills());
        assertEquals(5, candidate.getWorkExperienceYears());
        assertEquals(new BigDecimal("50000.00"), candidate.getAnnualSalary());
        assertEquals("Experienced Software Engineer", candidate.getProfileSummary());
        assertEquals("http://example.com/resume.pdf", candidate.getResumeUrl());
        assertEquals("http://example.com/picture.jpg", candidate.getProfilePictureUrl());
    }

    @Test
    void testSetters() {
        candidate.setFullName("Jane Doe");
        assertEquals("Jane Doe", candidate.getFullName());

        candidate.setMobileNumber("1234567890");
        assertEquals("1234567890", candidate.getMobileNumber());

        candidate.setGender("Female");
        assertEquals("Female", candidate.getGender());

        candidate.setDateOfBirth(LocalDate.of(1995, 5, 15));
        assertEquals(LocalDate.of(1995, 5, 15), candidate.getDateOfBirth());

        candidate.setWorkStatus(WorkStatus.EXPERIENCED);
        assertEquals(WorkStatus.EXPERIENCED, candidate.getWorkStatus());

        candidate.setCurrentLocation("San Francisco");
        assertEquals("San Francisco", candidate.getCurrentLocation());

        candidate.setHighestQualification("Master's");
        assertEquals("Master's", candidate.getHighestQualification());

        candidate.setCourse("Data Science");
        assertEquals("Data Science", candidate.getCourse());

        candidate.setSpecialization("Machine Learning");
        assertEquals("Machine Learning", candidate.getSpecialization());

        candidate.setUniversity("Stanford University");
        assertEquals("Stanford University", candidate.getUniversity());

        List<CandidateSkill> newSkills = new ArrayList<>();
        candidate.setKeySkills(newSkills);
        assertEquals(newSkills, candidate.getKeySkills());

        candidate.setWorkExperienceYears(7);
        assertEquals(7, candidate.getWorkExperienceYears());

        candidate.setAnnualSalary(new BigDecimal("75000.00"));
        assertEquals(new BigDecimal("75000.00"), candidate.getAnnualSalary());

        candidate.setProfileSummary("Experienced Data Scientist");
        assertEquals("Experienced Data Scientist", candidate.getProfileSummary());

        candidate.setResumeUrl("http://example.com/new_resume.pdf");
        assertEquals("http://example.com/new_resume.pdf", candidate.getResumeUrl());

        candidate.setProfilePictureUrl("http://example.com/new_picture.jpg");
        assertEquals("http://example.com/new_picture.jpg", candidate.getProfilePictureUrl());
    }
}
