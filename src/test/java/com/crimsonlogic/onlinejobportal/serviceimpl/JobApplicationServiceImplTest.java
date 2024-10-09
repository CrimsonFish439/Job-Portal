package com.crimsonlogic.onlinejobportal.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.crimsonlogic.onlinejobportal.dto.JobApplicationDTO;
import com.crimsonlogic.onlinejobportal.entity.JobApplication;
import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;
import com.crimsonlogic.onlinejobportal.repository.JobApplicationRepository;

class JobApplicationServiceImplTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private JobApplicationServiceImpl jobApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApplicationsByCandidateId() {
        // Arrange
        JobApplication jobApp1 = new JobApplication();
        jobApp1.setStatus(ApplicationStatus.APPLIED);

        JobApplication jobApp2 = new JobApplication();
        jobApp2.setStatus(ApplicationStatus.SHORTLISTED);

        List<JobApplication> applications = new ArrayList<>();
        applications.add(jobApp1);
        applications.add(jobApp2);

        // Mock repository response
        when(jobApplicationRepository.findByCandidateCandidateId("CND123")).thenReturn(applications);

        // Mock model mapping for both applications
        JobApplicationDTO dto1 = new JobApplicationDTO();
        dto1.setStatus(ApplicationStatus.APPLIED);

        JobApplicationDTO dto2 = new JobApplicationDTO();
        dto2.setStatus(ApplicationStatus.SHORTLISTED);

        // Simulate the behavior of the ModelMapper
        when(modelMapper.map(jobApp1, JobApplicationDTO.class)).thenReturn(dto1);
        when(modelMapper.map(jobApp2, JobApplicationDTO.class)).thenReturn(dto2);

        // Act
        List<JobApplicationDTO> result = jobApplicationService.getApplicationsByCandidateId("CND123");

        // Assert
        assertEquals(2, result.size());
        assertEquals(ApplicationStatus.APPLIED, result.get(0).getStatus());
        assertEquals(ApplicationStatus.SHORTLISTED, result.get(1).getStatus());
    }
}
