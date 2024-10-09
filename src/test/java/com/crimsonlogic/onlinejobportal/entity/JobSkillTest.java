package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JobSkillTest {

    private JobSkill jobSkill;
    private Job mockJob;
    private Skill mockSkill;

    @BeforeEach
    void setUp() {
        jobSkill = new JobSkill();
        mockJob = mock(Job.class);
        mockSkill = mock(Skill.class);

        jobSkill.setId(1L);
        jobSkill.setJob(mockJob);
        jobSkill.setSkill(mockSkill);
    }

    @Test
    void testGetters() {
        assertEquals(1L, jobSkill.getId());
        assertEquals(mockJob, jobSkill.getJob());
        assertEquals(mockSkill, jobSkill.getSkill());
    }

    @Test
    void testSetters() {
        Job newJob = mock(Job.class);
        Skill newSkill = mock(Skill.class);
        jobSkill.setJob(newJob);
        jobSkill.setSkill(newSkill);

        assertEquals(newJob, jobSkill.getJob());
        assertEquals(newSkill, jobSkill.getSkill());
    }
}
