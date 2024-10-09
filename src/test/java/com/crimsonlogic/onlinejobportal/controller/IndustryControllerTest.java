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

import com.crimsonlogic.onlinejobportal.entity.Industry;
import com.crimsonlogic.onlinejobportal.service.IndustryService;

class IndustryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IndustryService industryService;

    @InjectMocks
    private IndustryController industryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(industryController).build();
    }

    @Test
    void testGetAllIndustries() throws Exception {
        List<Industry> industries = Arrays.asList(new Industry(), new Industry());
        when(industryService.getAllIndustries()).thenReturn(industries);

        mockMvc.perform(get("/api/industries/getallindustries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(industryService, times(1)).getAllIndustries();
    }
}
