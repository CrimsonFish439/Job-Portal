package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.WorkStatus;

class CandidateDTOTest {

    private CandidateDTO candidateDTO;

    @BeforeEach
    void setUp() {
        candidateDTO = new CandidateDTO();
        List<String> selectedSkills = new ArrayList<>();
        selectedSkills.add("Java");
        selectedSkills.add("Python");

        candidateDTO.setCandidateId("CND12345");
        candidateDTO.setEmail("test@example.com");
        candidateDTO.setPassword("password123");
        candidateDTO.setFullName("John Doe");
        candidateDTO.setMobileNumber("9876543210");
        candidateDTO.setGender("Male");
        candidateDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        candidateDTO.setWorkStatus(WorkStatus.EXPERIENCED);
        candidateDTO.setCurrentLocation("New York");
        candidateDTO.setHighestQualification("Bachelor's");
        candidateDTO.setCourse("Computer Science");
        candidateDTO.setSpecialization("Software Engineering");
        candidateDTO.setUniversity("Harvard University");
        candidateDTO.setWorkExperienceYears(5);
        candidateDTO.setAnnualSalary(new BigDecimal("60000"));
        candidateDTO.setResumeUrl("http://example.com/resume.pdf");
        candidateDTO.setProfilePictureUrl("http://example.com/profile.jpg");
        candidateDTO.setProfileSummary("Experienced Software Engineer");
        candidateDTO.setSelectedSkills(selectedSkills);
    }

    @Test
    void testGetters() {
        assertEquals("CND12345", candidateDTO.getCandidateId());
        assertEquals("test@example.com", candidateDTO.getEmail());
        assertEquals("password123", candidateDTO.getPassword());
        assertEquals("John Doe", candidateDTO.getFullName());
        assertEquals("9876543210", candidateDTO.getMobileNumber());
        assertEquals("Male", candidateDTO.getGender());
        assertEquals(LocalDate.of(1990, 1, 1), candidateDTO.getDateOfBirth());
        assertEquals(WorkStatus.EXPERIENCED, candidateDTO.getWorkStatus());
        assertEquals("New York", candidateDTO.getCurrentLocation());
        assertEquals("Bachelor's", candidateDTO.getHighestQualification());
        assertEquals("Computer Science", candidateDTO.getCourse());
        assertEquals("Software Engineering", candidateDTO.getSpecialization());
        assertEquals("Harvard University", candidateDTO.getUniversity());
        assertEquals(5, candidateDTO.getWorkExperienceYears());
        assertEquals(new BigDecimal("60000"), candidateDTO.getAnnualSalary());
        assertEquals("http://example.com/resume.pdf", candidateDTO.getResumeUrl());
        assertEquals("http://example.com/profile.jpg", candidateDTO.getProfilePictureUrl());
        assertEquals("Experienced Software Engineer", candidateDTO.getProfileSummary());
        assertEquals(2, candidateDTO.getSelectedSkills().size());
    }

    @Test
    void testSetters() {
        List<String> newSkills = new ArrayList<>();
        newSkills.add("C++");

        candidateDTO.setFullName("Jane Doe");
        candidateDTO.setSelectedSkills(newSkills);

        assertEquals("Jane Doe", candidateDTO.getFullName());
        assertEquals(1, candidateDTO.getSelectedSkills().size());
        assertEquals("C++", candidateDTO.getSelectedSkills().get(0));
    }
}
