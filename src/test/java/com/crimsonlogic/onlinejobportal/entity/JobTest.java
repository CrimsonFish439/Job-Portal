package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.enums.EmploymentType;
import com.crimsonlogic.onlinejobportal.enums.WorkMode;

class JobTest {

    private Job job;
    private Recruiter mockRecruiter;
    private List<JobSkill> mockSkills;
    private List<JobLocation> mockLocations;

    @BeforeEach
    void setUp() {
        // Initialize a new Job object before each test
        job = new Job();

        // Mock dependencies (Recruiter, JobSkill, and JobLocation)
        mockRecruiter = mock(Recruiter.class);
        mockSkills = new ArrayList<>();
        mockLocations = new ArrayList<>();

        mockSkills.add(mock(JobSkill.class));
        mockLocations.add(mock(JobLocation.class));

        // Set properties of the job
        job.setJobId("JOB12345");
        job.setJobTitle("Software Engineer");
        job.setEmploymentType(EmploymentType.FULL_TIME);
        job.setKeySkills(mockSkills);
        job.setJobLocations(mockLocations);
        job.setDepartment("Engineering");
        job.setWorkMode(WorkMode.HYBRID);
        job.setMinExperience(2);
        job.setMaxExperience(5);
        job.setMinSalary(50000);
        job.setMaxSalary(120000);
        job.setQualification("Bachelor's Degree");
        job.setCourse("Computer Science");
        job.setSpecialization("Software Engineering");
        job.setJobDescription("Responsible for developing software solutions.");
        job.setVacancies(3);
        job.setRecruiter(mockRecruiter);
    }

    @Test
    void testGetters() {
        assertEquals("JOB12345", job.getJobId());
        assertEquals("Software Engineer", job.getJobTitle());
        assertEquals(EmploymentType.FULL_TIME, job.getEmploymentType());
        assertEquals(mockSkills, job.getKeySkills());
        assertEquals(mockLocations, job.getJobLocations());
        assertEquals("Engineering", job.getDepartment());
        assertEquals(WorkMode.HYBRID, job.getWorkMode());
        assertEquals(2, job.getMinExperience());
        assertEquals(5, job.getMaxExperience());
        assertEquals(50000, job.getMinSalary());
        assertEquals(120000, job.getMaxSalary());
        assertEquals("Bachelor's Degree", job.getQualification());
        assertEquals("Computer Science", job.getCourse());
        assertEquals("Software Engineering", job.getSpecialization());
        assertEquals("Responsible for developing software solutions.", job.getJobDescription());
        assertEquals(3, job.getVacancies());
        assertEquals(mockRecruiter, job.getRecruiter());
    }

    @Test
    void testSetters() {
        job.setJobId("JOB67890");
        assertEquals("JOB67890", job.getJobId());

        job.setJobTitle("Data Scientist");
        assertEquals("Data Scientist", job.getJobTitle());

        job.setEmploymentType(EmploymentType.PART_TIME);
        assertEquals(EmploymentType.PART_TIME, job.getEmploymentType());

        List<JobSkill> newSkills = new ArrayList<>();
        job.setKeySkills(newSkills);
        assertEquals(newSkills, job.getKeySkills());

        List<JobLocation> newLocations = new ArrayList<>();
        job.setJobLocations(newLocations);
        assertEquals(newLocations, job.getJobLocations());

        job.setDepartment("Data Science");
        assertEquals("Data Science", job.getDepartment());

        job.setWorkMode(WorkMode.HYBRID);
        assertEquals(WorkMode.HYBRID, job.getWorkMode());

        job.setMinExperience(3);
        assertEquals(3, job.getMinExperience());

        job.setMaxExperience(7);
        assertEquals(7, job.getMaxExperience());

        job.setMinSalary(70000);
        assertEquals(70000, job.getMinSalary());

        job.setMaxSalary(150000);
        assertEquals(150000, job.getMaxSalary());

        job.setQualification("Master's Degree");
        assertEquals("Master's Degree", job.getQualification());

        job.setCourse("Data Science");
        assertEquals("Data Science", job.getCourse());

        job.setSpecialization("Machine Learning");
        assertEquals("Machine Learning", job.getSpecialization());

        job.setJobDescription("Responsible for developing AI solutions.");
        assertEquals("Responsible for developing AI solutions.", job.getJobDescription());

        job.setVacancies(5);
        assertEquals(5, job.getVacancies());

        Recruiter newRecruiter = mock(Recruiter.class);
        job.setRecruiter(newRecruiter);
        assertEquals(newRecruiter, job.getRecruiter());
    }
}
