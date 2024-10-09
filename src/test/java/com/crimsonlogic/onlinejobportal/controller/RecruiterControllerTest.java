package com.crimsonlogic.onlinejobportal.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.onlinejobportal.dto.LoginRequestDTO;
import com.crimsonlogic.onlinejobportal.dto.RecruiterDTO;
import com.crimsonlogic.onlinejobportal.enums.EmployeeRange;
import com.crimsonlogic.onlinejobportal.service.RecruiterService;
import com.fasterxml.jackson.databind.ObjectMapper;

class RecruiterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RecruiterService recruiterService;

    @InjectMocks
    private RecruiterController recruiterController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recruiterController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveRecruiter() throws Exception {
        MockMultipartFile companyLogo = new MockMultipartFile("companyLogo", "logo.png", "image/png", "company-logo".getBytes());

        mockMvc.perform(multipart("/api/recruiter/register")
                .file(companyLogo)
                .param("fullName", "John Doe")
                .param("officialEmail", "john@example.com")
                .param("password", "password123")
                .param("companyName", "Tech Corp")
                .param("employeeRange", EmployeeRange.RANGE_100_250.toString())
                .param("designation", "HR Manager")
                .param("companyLocation", "New York")
                .param("companyAddress", "123 Tech Street")
                .param("aboutCompany", "Tech Corp is a global leader in technology.")
                .param("industry", "IT")
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().string("Recruiter saved successfully!"));

        verify(recruiterService, times(1)).saveRecruiter(any(RecruiterDTO.class));
    }

    @Test
    void testLoginRecruiter() throws Exception {
        // Create a new LoginRequestDTO instance and set the values using setters
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setEmail("john@example.com");
        loginRequestDTO.setPassword("password123");

        // Mock the service response
        when(recruiterService.loginRecruiter("john@example.com", "password123")).thenReturn(true);

        // Perform the login request
        mockMvc.perform(post("/api/recruiter/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));

        verify(recruiterService, times(1)).loginRecruiter(anyString(), anyString());
    }

}
