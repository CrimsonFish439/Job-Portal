package com.crimsonlogic.onlinejobportal.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.crimsonlogic.onlinejobportal.dto.SpecializationDTO;
import com.crimsonlogic.onlinejobportal.service.SpecializationService;

class SpecializationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SpecializationService specializationService;

    @InjectMocks
    private SpecializationController specializationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(specializationController).build();
    }

    @Test
    void testGetSpecializationsByCourse() throws Exception {
        SpecializationDTO specialization1 = new SpecializationDTO();
        specialization1.setSpecializationId(1L);
        specialization1.setSpecializationName("Software Engineering");

        SpecializationDTO specialization2 = new SpecializationDTO();
        specialization2.setSpecializationId(2L);
        specialization2.setSpecializationName("Data Science");

        List<SpecializationDTO> specializations = Arrays.asList(specialization1, specialization2);
        when(specializationService.getSpecializationsByCourse("Computer Science")).thenReturn(specializations);

        mockMvc.perform(get("/api/specializations/bycourse/Computer%20Science"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].specializationName").value("Software Engineering"))
                .andExpect(jsonPath("$[1].specializationName").value("Data Science"));

        verify(specializationService, times(1)).getSpecializationsByCourse("Computer Science");
    }

}
