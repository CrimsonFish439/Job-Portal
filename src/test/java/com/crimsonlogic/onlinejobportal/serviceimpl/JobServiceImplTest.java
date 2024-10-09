package com.crimsonlogic.onlinejobportal.serviceimpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.crimsonlogic.onlinejobportal.dto.JobDTO;
import com.crimsonlogic.onlinejobportal.entity.Job;
import com.crimsonlogic.onlinejobportal.entity.JobLocation;
import com.crimsonlogic.onlinejobportal.entity.JobSkill;
import com.crimsonlogic.onlinejobportal.entity.Location;
import com.crimsonlogic.onlinejobportal.entity.Recruiter;
import com.crimsonlogic.onlinejobportal.entity.Skill;
import com.crimsonlogic.onlinejobportal.enums.EmploymentType;
import com.crimsonlogic.onlinejobportal.repository.CandidateRepository;
import com.crimsonlogic.onlinejobportal.repository.JobRepository;
import com.crimsonlogic.onlinejobportal.repository.LocationRepository;
import com.crimsonlogic.onlinejobportal.repository.RecruiterRepository;
import com.crimsonlogic.onlinejobportal.repository.SkillRepository;

class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private RecruiterRepository recruiterRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveJob() {
        // Arrange
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobTitle("Software Developer");
        jobDTO.setEmploymentType(EmploymentType.FULL_TIME);
        jobDTO.setRecruiterEmail("recruiter@example.com");
        jobDTO.setKeySkillsIds(Arrays.asList("skill1", "skill2"));
        jobDTO.setJobLocationIds(Arrays.asList("loc1", "loc2"));

        Recruiter recruiter = new Recruiter();
        when(recruiterRepository.findByUserEmail(jobDTO.getRecruiterEmail())).thenReturn(recruiter);

        Skill skill1 = new Skill();
        when(skillRepository.findBySkillId("skill1")).thenReturn(Optional.of(skill1));
        Skill skill2 = new Skill();
        when(skillRepository.findBySkillId("skill2")).thenReturn(Optional.of(skill2));

        Location loc1 = new Location();
        when(locationRepository.findByLocationId("loc1")).thenReturn(Optional.of(loc1));
        Location loc2 = new Location();
        when(locationRepository.findByLocationId("loc2")).thenReturn(Optional.of(loc2));

        // Act
        jobService.saveJob(jobDTO);

        // Assert
        verify(jobRepository, times(1)).save(any(Job.class));
    }

    @Test
    void testGetJobById() {
        // Arrange
        Job job = new Job();
        job.setJobId("job1");
        job.setJobTitle("Software Developer");

        // Initialize job location and set the location to avoid NullPointerException
        JobLocation jobLocation = new JobLocation();
        Location location = new Location();
        location.setLocationName("Location1"); // Set a location name
        jobLocation.setLocation(location); // Set the location for JobLocation

        job.setJobLocations(Arrays.asList(jobLocation)); // Add jobLocation to the job

        // Initialize key skills to avoid NullPointerException
        Skill skill1 = new Skill();
        skill1.setSkillName("Java");
        Skill skill2 = new Skill();
        skill2.setSkillName("Spring");

        JobSkill jobSkill1 = new JobSkill();
        jobSkill1.setSkill(skill1);
        JobSkill jobSkill2 = new JobSkill();
        jobSkill2.setSkill(skill2);

        job.setKeySkills(Arrays.asList(jobSkill1, jobSkill2)); // Add keySkills to the job

        when(jobRepository.findById("job1")).thenReturn(Optional.of(job));

        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId("job1");
        jobDTO.setJobTitle("Software Developer");
        jobDTO.setJobLocationIds(Arrays.asList("Location1")); // Simulating a job location in DTO
        jobDTO.setKeySkillsIds(Arrays.asList("Java", "Spring")); // Simulating key skills in DTO

        when(modelMapper.map(job, JobDTO.class)).thenReturn(jobDTO);

        // Act
        JobDTO result = jobService.getJobById("job1");

        // Assert
        assertNotNull(result);
        assertEquals("job1", result.getJobId());
        assertEquals("Software Developer", result.getJobTitle());
        assertEquals(Arrays.asList("Java", "Spring"), result.getKeySkillsIds()); // Validate key skills
    }

    @Test
    void testGetAllJobs() {
        // Arrange
        Job job1 = new Job();
        job1.setJobId("job1");
        job1.setJobTitle("Software Developer");

        // Initialize job locations for job1
        JobLocation jobLocation1 = new JobLocation();
        Location location1 = new Location();
        location1.setLocationName("Location1");
        jobLocation1.setLocation(location1);
        job1.setJobLocations(Arrays.asList(jobLocation1)); // Add jobLocation1 to job1

        // Initialize key skills for job1
        Skill skill1 = new Skill();
        skill1.setSkillName("Java");
        Skill skill2 = new Skill();
        skill2.setSkillName("Spring");
        JobSkill jobSkill1 = new JobSkill();
        jobSkill1.setSkill(skill1);
        JobSkill jobSkill2 = new JobSkill();
        jobSkill2.setSkill(skill2);
        job1.setKeySkills(Arrays.asList(jobSkill1, jobSkill2)); // Add skills to job1

        Job job2 = new Job();
        job2.setJobId("job2");
        job2.setJobTitle("Data Analyst");

        // Initialize job locations for job2
        JobLocation jobLocation2 = new JobLocation();
        Location location2 = new Location();
        location2.setLocationName("Location2");
        jobLocation2.setLocation(location2);
        job2.setJobLocations(Arrays.asList(jobLocation2)); // Add jobLocation2 to job2

        // Initialize key skills for job2
        Skill skill3 = new Skill();
        skill3.setSkillName("Python");
        Skill skill4 = new Skill();
        skill4.setSkillName("Machine Learning");
        JobSkill jobSkill3 = new JobSkill();
        jobSkill3.setSkill(skill3);
        JobSkill jobSkill4 = new JobSkill();
        jobSkill4.setSkill(skill4);
        job2.setKeySkills(Arrays.asList(jobSkill3, jobSkill4)); // Add skills to job2

        List<Job> jobs = Arrays.asList(job1, job2);

        when(jobRepository.findAll()).thenReturn(jobs);

        // Act
        List<JobDTO> result = jobService.getAllJobs();

        // Assert
        assertEquals(2, result.size());
        assertEquals("job1", result.get(0).getJobId());
        assertEquals("job2", result.get(1).getJobId());
        assertEquals(Arrays.asList("Location1"), result.get(0).getJobLocationIds()); // Validate location for job1
        assertEquals(Arrays.asList("Location2"), result.get(1).getJobLocationIds()); // Validate location for job2
        assertEquals(Arrays.asList("Java", "Spring"), result.get(0).getKeySkillsIds()); // Validate skills for job1
        assertEquals(Arrays.asList("Python", "Machine Learning"), result.get(1).getKeySkillsIds()); // Validate skills for job2
    }


}
