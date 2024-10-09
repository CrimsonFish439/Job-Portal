package com.crimsonlogic.onlinejobportal.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crimsonlogic.onlinejobportal.dto.QualificationDTO;
import com.crimsonlogic.onlinejobportal.entity.Qualification;
import com.crimsonlogic.onlinejobportal.repository.QualificationRepository;

class QualificationServiceImplTest {

    @Mock
    private QualificationRepository qualificationRepository;

    @InjectMocks
    private QualificationServiceImpl qualificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQualifications() {
        // Arrange
        Qualification qualification1 = new Qualification();
        qualification1.setQualificationId(1L);
        qualification1.setQualificationName("Bachelor's");

        Qualification qualification2 = new Qualification();
        qualification2.setQualificationId(1L);
        qualification2.setQualificationName("Master's");

        List<Qualification> qualifications = Arrays.asList(qualification1, qualification2);

        when(qualificationRepository.findAll()).thenReturn(qualifications);

        // Act
        List<QualificationDTO> result = qualificationService.getAllQualifications();

        // Assert
        assertEquals(2, result.size());
        assertEquals(1l, result.get(0).getQualificationId());
        assertEquals("Bachelor's", result.get(0).getQualificationName());
        assertEquals(1L, result.get(1).getQualificationId());
        assertEquals("Master's", result.get(1).getQualificationName());
    }
}
