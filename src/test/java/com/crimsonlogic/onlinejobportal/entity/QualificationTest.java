package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QualificationTest {

    private Qualification qualification;
    private List<Course> mockCourses;

    @BeforeEach
    void setUp() {
        qualification = new Qualification();
        mockCourses = new ArrayList<>();
        mockCourses.add(mock(Course.class));

        qualification.setQualificationId(1L);
        qualification.setQualificationName("Bachelor's Degree");
        qualification.setCourses(mockCourses);
    }

    @Test
    void testGetters() {
        assertEquals(1L, qualification.getQualificationId());
        assertEquals("Bachelor's Degree", qualification.getQualificationName());
        assertEquals(mockCourses, qualification.getCourses());
    }

    @Test
    void testSetters() {
        List<Course> newCourses = new ArrayList<>();
        qualification.setQualificationId(2L);
        qualification.setQualificationName("Master's Degree");
        qualification.setCourses(newCourses);

        assertEquals(2L, qualification.getQualificationId());
        assertEquals("Master's Degree", qualification.getQualificationName());
        assertEquals(newCourses, qualification.getCourses());
    }
}
