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

import com.crimsonlogic.onlinejobportal.dto.UniversityDTO;
import com.crimsonlogic.onlinejobportal.entity.University;
import com.crimsonlogic.onlinejobportal.repository.UniversityRepository;

class UniversityServiceImplTest {

    @Mock
    private UniversityRepository universityRepository;

    @InjectMocks
    private UniversityServiceImpl universityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUniversities() {
        // Arrange
        University university1 = new University();
        university1.setUniversityId(123L);
        university1.setUniversityName("Harvard University");

        University university2 = new University();
        university2.setUniversityId(456L);
        university2.setUniversityName("Stanford University");

        when(universityRepository.findAll()).thenReturn(Arrays.asList(university1, university2));

        // Act
        List<UniversityDTO> result = universityService.getAllUniversities();

        // Assert
        assertEquals(2, result.size());
        assertEquals(123L, result.get(0).getUniversityId());
        assertEquals("Harvard University", result.get(0).getUniversityName());
        assertEquals(456L, result.get(1).getUniversityId());
        assertEquals("Stanford University", result.get(1).getUniversityName());
    }
}
