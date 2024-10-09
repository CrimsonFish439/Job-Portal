package com.crimsonlogic.onlinejobportal.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.LoginRequestDTO;
import com.crimsonlogic.onlinejobportal.entity.JobApplication;
import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;
import com.crimsonlogic.onlinejobportal.enums.WorkStatus;
import com.crimsonlogic.onlinejobportal.service.CandidateService;
import com.crimsonlogic.onlinejobportal.service.JobApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

class CandidateControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CandidateService candidateService;

    @Mock
    private JobApplicationService jobApplicationService;

    @InjectMocks
    private CandidateController candidateController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveCandidate() throws Exception {
        MockMultipartFile picture = new MockMultipartFile("picture", "profile.jpg", "image/jpeg", "some-image".getBytes());
        MockMultipartFile resume = new MockMultipartFile("resume", "resume.pdf", "application/pdf", "some-resume".getBytes());

        mockMvc.perform(multipart("/api/candidates/save")
                .file(picture)
                .file(resume)
                .param("fullName", "John Doe")
                .param("email", "john@example.com")
                .param("password", "password123")
                .param("mobileNumber", "1234567890")
                .param("workStatus", WorkStatus.EXPERIENCED.toString())
                .param("currentLocation", "New York")
                .param("workExperienceYears", "5")
                .param("annualSalary", "50000")
                .param("highestQualification", "Bachelor's")
                .param("selectedCourse", "Computer Science")
                .param("selectedSpecialization", "Software Engineering")
                .param("selectedUniversity", "Harvard")
                .param("selectedSkills", "Java")
                .param("dateOfBirth", LocalDate.of(1990, 1, 1).toString())
                .param("gender", "Male")
                .param("profileDescription", "Experienced Engineer")
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().string("Candidate saved successfully!"));

        verify(candidateService, times(1)).saveCandidate(any(CandidateDTO.class));
    }

    @Test
    void testLoginCandidate() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO();
        loginRequest.setEmail("john@example.com");
        loginRequest.setPassword("password123");

        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateId("CND12345");
        candidateDTO.setEmail("john@example.com");

        when(candidateService.loginCandidate(anyString(), anyString())).thenReturn(true);
        when(candidateService.getCandidateByEmail(anyString())).thenReturn(candidateDTO);

        mockMvc.perform(post("/api/candidates/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.candidateId").value("CND12345"))
                .andExpect(jsonPath("$.email").value("john@example.com"));

        verify(candidateService, times(1)).loginCandidate(anyString(), anyString());
        verify(candidateService, times(1)).getCandidateByEmail(anyString());
    }

    @Test
    void testGetCandidateDetails() throws Exception {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setEmail("john@example.com");
        candidateDTO.setFullName("John Doe");

        when(candidateService.getCandidateByEmail(anyString())).thenReturn(candidateDTO);

        mockMvc.perform(get("/api/candidates/details")
                .param("email", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.fullName").value("John Doe"));

        verify(candidateService, times(1)).getCandidateByEmail(anyString());
    }

    @Test
    void testChangeEmail() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("currentEmail", "john@example.com");
        request.put("newEmail", "john.doe@example.com");

        when(candidateService.updateEmail(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/api/candidates/changeEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Email updated successfully."));

        verify(candidateService, times(1)).updateEmail(anyString(), anyString());
    }

    @Test
    void testChangePassword() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("email", "john@example.com");
        request.put("currentPassword", "password123");
        request.put("newPassword", "newpassword123");

        when(candidateService.updatePassword(anyString(), anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/api/candidates/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Password updated successfully."));

        verify(candidateService, times(1)).updatePassword(anyString(), anyString(), anyString());
    }

    @Test
    void testGetCandidateById() throws Exception {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateId("CND12345");

        when(candidateService.getCandidateById(anyString())).thenReturn(candidateDTO);

        mockMvc.perform(get("/api/candidates/CND12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.candidateId").value("CND12345"));

        verify(candidateService, times(1)).getCandidateById(anyString());
    }

    @Test
    void testGetCandidateProfileWithStatus() throws Exception {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateId("CND12345");

        JobApplication jobApplication = new JobApplication();
        jobApplication.setStatus(ApplicationStatus.APPLIED);

        when(candidateService.getCandidateById(anyString())).thenReturn(candidateDTO);
        when(jobApplicationService.getApplicationByCandidateAndJob(anyString(), anyString())).thenReturn(jobApplication);

        mockMvc.perform(get("/api/candidates/CND12345/job/JOB12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.candidate.candidateId").value("CND12345"))
                .andExpect(jsonPath("$.status").value("APPLIED"));

        verify(candidateService, times(1)).getCandidateById(anyString());
        verify(jobApplicationService, times(1)).getApplicationByCandidateAndJob(anyString(), anyString());
    }
}
