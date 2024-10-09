package com.crimsonlogic.onlinejobportal.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.JobDTO;
import com.crimsonlogic.onlinejobportal.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;

class JobControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testPostJob() throws Exception {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobTitle("Software Engineer");

        mockMvc.perform(post("/api/job/postjob")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jobDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Job posted successfully!"));

        verify(jobService, times(1)).saveJob(any(JobDTO.class));
    }

    @Test
    void testGetJobsByRecruiter() throws Exception {
        List<JobDTO> jobs = Arrays.asList(new JobDTO(), new JobDTO());
        when(jobService.getJobsByRecruiter("recruiter@example.com")).thenReturn(jobs);

        mockMvc.perform(get("/api/job/recruiter-jobs")
                .param("recruiterEmail", "recruiter@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(jobService, times(1)).getJobsByRecruiter("recruiter@example.com");
    }

    @Test
    void testGetAllJobs() throws Exception {
        List<JobDTO> jobs = Arrays.asList(new JobDTO(), new JobDTO());
        when(jobService.getAllJobs()).thenReturn(jobs);

        mockMvc.perform(get("/api/job/all-jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(jobService, times(1)).getAllJobs();
    }

    @Test
    void testGetJobById() throws Exception {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobId("JOB123");

        when(jobService.getJobById("JOB123")).thenReturn(jobDTO);

        mockMvc.perform(get("/api/job/job-details")
                .param("jobId", "JOB123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobId").value("JOB123"));

        verify(jobService, times(1)).getJobById("JOB123");
    }

    @Test
    void testGetCandidatesByJobId() throws Exception {
        List<CandidateDTO> candidates = Arrays.asList(new CandidateDTO(), new CandidateDTO());
        when(jobService.getCandidatesByJobId("JOB123")).thenReturn(candidates);

        mockMvc.perform(get("/api/job/candidates")
                .param("jobId", "JOB123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(jobService, times(1)).getCandidatesByJobId("JOB123");
    }
}
