package com.crimsonlogic.onlinejobportal.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDTOTest {

    private CourseDTO courseDTO;

    @BeforeEach
    void setUp() {
        courseDTO = new CourseDTO();
        courseDTO.setCourseId(1L);
        courseDTO.setCourseName("Computer Science");
    }

    @Test
    void testGetters() {
        assertEquals(1L, courseDTO.getCourseId());
        assertEquals("Computer Science", courseDTO.getCourseName());
    }

    @Test
    void testSetters() {
        courseDTO.setCourseName("Data Science");
        assertEquals("Data Science", courseDTO.getCourseName());
    }
}
