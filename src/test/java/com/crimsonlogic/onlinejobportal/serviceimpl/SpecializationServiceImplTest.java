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

import com.crimsonlogic.onlinejobportal.dto.SpecializationDTO;
import com.crimsonlogic.onlinejobportal.entity.Specialization;
import com.crimsonlogic.onlinejobportal.repository.SpecializationRepository;

class SpecializationServiceImplTest {

    @Mock
    private SpecializationRepository specializationRepository;

    @InjectMocks
    private SpecializationServiceImpl specializationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSpecializationsByCourse() {
        // Arrange
        Specialization specialization1 = new Specialization();
        specialization1.setSpecializationId(1L);
        specialization1.setSpecializationName("Computer Science");

        Specialization specialization2 = new Specialization();
        specialization2.setSpecializationId(1L);
        specialization2.setSpecializationName("Mechanical Engineering");

        when(specializationRepository.findByCourse_CourseName("Engineering")).thenReturn(Arrays.asList(specialization1, specialization2));

        // Act
        List<SpecializationDTO> result = specializationService.getSpecializationsByCourse("Engineering");

        // Assert
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getSpecializationId());
        assertEquals("Computer Science", result.get(0).getSpecializationName());
        assertEquals(1L, result.get(1).getSpecializationId());
        assertEquals("Mechanical Engineering", result.get(1).getSpecializationName());
    }
}
