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

import com.crimsonlogic.onlinejobportal.entity.Industry;
import com.crimsonlogic.onlinejobportal.repository.IndustryRepository;

class IndustryServiceImplTest {

    @Mock
    private IndustryRepository industryRepository;

    @InjectMocks
    private IndustryServiceImpl industryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllIndustries() {
        // Arrange
        Industry industry1 = new Industry();
        industry1.setIndustryId("1");
        industry1.setIndustryName("IT");

        Industry industry2 = new Industry();
        industry2.setIndustryId("2");
        industry2.setIndustryName("Finance");

        when(industryRepository.findAll()).thenReturn(Arrays.asList(industry1, industry2));

        // Act
        List<Industry> result = industryService.getAllIndustries();

        // Assert
        assertEquals(2, result.size());
        assertEquals("IT", result.get(0).getIndustryName());
        assertEquals("Finance", result.get(1).getIndustryName());
    }
}
