package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.crimsonlogic.onlinejobportal.entity.Course;

class QualificationDTOTest {

    private QualificationDTO qualificationDTO;
    private List<Course> courses;

    @BeforeEach
    void setUp() {
        qualificationDTO = new QualificationDTO();
        qualificationDTO.setQualificationId(1L);
        qualificationDTO.setQualificationName("Bachelor's Degree");

        courses = new ArrayList<>();
        qualificationDTO.setCourses(courses);
    }

    @Test
    void testGetters() {
        assertEquals(1L, qualificationDTO.getQualificationId());
        assertEquals("Bachelor's Degree", qualificationDTO.getQualificationName());
        assertEquals(courses, qualificationDTO.getCourses());
    }

    @Test
    void testSetters() {
        qualificationDTO.setQualificationName("Master's Degree");
        assertEquals("Master's Degree", qualificationDTO.getQualificationName());
    }
}
