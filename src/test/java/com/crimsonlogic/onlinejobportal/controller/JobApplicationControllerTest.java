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

import com.crimsonlogic.onlinejobportal.dto.JobApplicationDTO;
import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;
import com.crimsonlogic.onlinejobportal.service.JobApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

class JobApplicationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JobApplicationService jobApplicationService;

    @InjectMocks
    private JobApplicationController jobApplicationController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobApplicationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testApplyForJob() throws Exception {
        JobApplicationDTO jobApplicationDTO = new JobApplicationDTO();
        jobApplicationDTO.setCandidateId("CND123");
        jobApplicationDTO.setJobId("JOB123");

        mockMvc.perform(post("/api/job-applications/apply")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(jobApplicationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Job application submitted successfully"));

        verify(jobApplicationService, times(1)).applyForJob(any(JobApplicationDTO.class));
    }

    @Test
    void testShortlistCandidate() throws Exception {
        mockMvc.perform(post("/api/job-applications/CND123/job/JOB123/shortlist"))
                .andExpect(status().isOk())
                .andExpect(content().string("Candidate shortlisted"));

        verify(jobApplicationService, times(1)).updateApplicationStatus("CND123", "JOB123", ApplicationStatus.SHORTLISTED);
    }

    @Test
    void testRejectCandidate() throws Exception {
        mockMvc.perform(post("/api/job-applications/CND123/job/JOB123/reject"))
                .andExpect(status().isOk())
                .andExpect(content().string("Candidate rejected"));

        verify(jobApplicationService, times(1)).updateApplicationStatus("CND123", "JOB123", ApplicationStatus.REJECTED);
    }

    @Test
    void testAcceptCandidate() throws Exception {
        mockMvc.perform(post("/api/job-applications/CND123/job/JOB123/accept"))
                .andExpect(status().isOk())
                .andExpect(content().string("Candidate accepted"));

        verify(jobApplicationService, times(1)).updateApplicationStatus("CND123", "JOB123", ApplicationStatus.SELECTED);
    }

    @Test
    void testGetCandidateApplications() throws Exception {
        List<JobApplicationDTO> applications = Arrays.asList(new JobApplicationDTO(), new JobApplicationDTO());
        when(jobApplicationService.getApplicationsByCandidateId("CND123")).thenReturn(applications);

        mockMvc.perform(get("/api/job-applications/candidate/CND123/applications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(jobApplicationService, times(1)).getApplicationsByCandidateId("CND123");
    }
}
