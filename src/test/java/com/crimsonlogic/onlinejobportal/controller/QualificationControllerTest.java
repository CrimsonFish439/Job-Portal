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

import com.crimsonlogic.onlinejobportal.dto.QualificationDTO;
import com.crimsonlogic.onlinejobportal.service.QualificationService;

class QualificationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QualificationService qualificationService;

    @InjectMocks
    private QualificationController qualificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(qualificationController).build();
    }

    @Test
    void testGetAllQualifications() throws Exception {
        QualificationDTO qualification1 = new QualificationDTO();
        qualification1.setQualificationId(1L);
        qualification1.setQualificationName("Bachelor's");

        QualificationDTO qualification2 = new QualificationDTO();
        qualification2.setQualificationId(2L);
        qualification2.setQualificationName("Master's");

        List<QualificationDTO> qualifications = Arrays.asList(qualification1, qualification2);
        when(qualificationService.getAllQualifications()).thenReturn(qualifications);

        mockMvc.perform(get("/api/qualifications/getallqualifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].qualificationName").value("Bachelor's"))
                .andExpect(jsonPath("$[1].qualificationName").value("Master's"));

        verify(qualificationService, times(1)).getAllQualifications();
    }
}
