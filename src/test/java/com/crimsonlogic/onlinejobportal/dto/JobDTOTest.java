package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.EmploymentType;
import com.crimsonlogic.onlinejobportal.enums.WorkMode;

class JobDTOTest {

    private JobDTO jobDTO;

    @BeforeEach
    void setUp() {
        jobDTO = new JobDTO();
        jobDTO.setJobId("JOB12345");
        jobDTO.setJobTitle("Software Engineer");
        jobDTO.setEmploymentType(EmploymentType.FULL_TIME);
        jobDTO.setWorkMode(WorkMode.IN_OFFICE);
        jobDTO.setVacancies(5);
        List<String> skillIds = new ArrayList<>();
        skillIds.add("SKL123");
        jobDTO.setKeySkillsIds(skillIds);
    }

    @Test
    void testGetters() {
        assertEquals("JOB12345", jobDTO.getJobId());
        assertEquals("Software Engineer", jobDTO.getJobTitle());
        assertEquals(EmploymentType.FULL_TIME, jobDTO.getEmploymentType());
        assertEquals(WorkMode.IN_OFFICE, jobDTO.getWorkMode());
        assertEquals(5, jobDTO.getVacancies());
        assertEquals(1, jobDTO.getKeySkillsIds().size());
    }

    @Test
    void testSetters() {
        List<String> newSkills = new ArrayList<>();
        newSkills.add("SKL456");
        jobDTO.setKeySkillsIds(newSkills);
        assertEquals(1, jobDTO.getKeySkillsIds().size());
        assertEquals("SKL456", jobDTO.getKeySkillsIds().get(0));
    }
}
