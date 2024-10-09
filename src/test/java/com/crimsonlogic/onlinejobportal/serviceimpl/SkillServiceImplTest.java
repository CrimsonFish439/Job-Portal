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

import com.crimsonlogic.onlinejobportal.dto.SkillDTO;
import com.crimsonlogic.onlinejobportal.entity.Skill;
import com.crimsonlogic.onlinejobportal.repository.SkillRepository;

class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSkills() {
        // Arrange
        Skill skill1 = new Skill();
        skill1.setSkillId("skill1");
        skill1.setSkillName("Java");

        Skill skill2 = new Skill();
        skill2.setSkillId("skill2");
        skill2.setSkillName("Spring Boot");

        when(skillRepository.findAll()).thenReturn(Arrays.asList(skill1, skill2));

        // Act
        List<SkillDTO> result = skillService.getAllSkills();

        // Assert
        assertEquals(2, result.size());
        assertEquals("skill1", result.get(0).getSkillId());
        assertEquals("Java", result.get(0).getSkillName());
        assertEquals("skill2", result.get(1).getSkillId());
        assertEquals("Spring Boot", result.get(1).getSkillName());
    }
}

