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

import com.crimsonlogic.onlinejobportal.dto.UniversityDTO;
import com.crimsonlogic.onlinejobportal.service.UniversityService;

class UniversityControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UniversityService universityService;

    @InjectMocks
    private UniversityController universityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(universityController).build();
    }

    @Test
    void testGetAllUniversities() throws Exception {
        UniversityDTO university1 = new UniversityDTO();
        university1.setUniversityId(1L);
        university1.setUniversityName("Harvard University");

        UniversityDTO university2 = new UniversityDTO();
        university2.setUniversityId(2L);
        university2.setUniversityName("Stanford University");

        List<UniversityDTO> universities = Arrays.asList(university1, university2);
        when(universityService.getAllUniversities()).thenReturn(universities);

        mockMvc.perform(get("/api/universities/getalluniversities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].universityName").value("Harvard University"))
                .andExpect(jsonPath("$[1].universityName").value("Stanford University"));

        verify(universityService, times(1)).getAllUniversities();
    }

}
