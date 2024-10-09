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

import com.crimsonlogic.onlinejobportal.dto.SkillDTO;
import com.crimsonlogic.onlinejobportal.service.SkillService;

class SkillControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SkillService skillService;

    @InjectMocks
    private SkillController skillController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(skillController).build();
    }

    @Test
    void testGetAllSkills() throws Exception {
        List<SkillDTO> skills = Arrays.asList(
            new SkillDTO("SKL123", "Java"),
            new SkillDTO("SKL124", "Python")
        );
        when(skillService.getAllSkills()).thenReturn(skills);

        mockMvc.perform(get("/api/skills/getallskills"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].skillName").value("Java"))
                .andExpect(jsonPath("$[1].skillName").value("Python"));

        verify(skillService, times(1)).getAllSkills();
    }
}
