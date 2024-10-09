package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseTest {

    private Course course;
    private Qualification mockQualification;
    private List<Specialization> mockSpecializations;

    @BeforeEach
    void setUp() {
        // Initialize a new Course object before each test
        course = new Course();

        // Mock dependencies (Qualification and Specializations)
        mockQualification = mock(Qualification.class);
        mockSpecializations = new ArrayList<>();
        mockSpecializations.add(mock(Specialization.class));
        mockSpecializations.add(mock(Specialization.class));

        // Set properties of the course
        course.setCourseId(1L);
        course.setCourseName("Computer Science");
        course.setQualification(mockQualification);
        course.setSpecializations(mockSpecializations);
    }

    @Test
    void testGetters() {
        assertEquals(1L, course.getCourseId());
        assertEquals("Computer Science", course.getCourseName());
        assertEquals(mockQualification, course.getQualification());
        assertEquals(mockSpecializations, course.getSpecializations());
    }

    @Test
    void testSetters() {
        course.setCourseId(2L);
        assertEquals(2L, course.getCourseId());

        course.setCourseName("Data Science");
        assertEquals("Data Science", course.getCourseName());

        Qualification newQualification = mock(Qualification.class);
        course.setQualification(newQualification);
        assertEquals(newQualification, course.getQualification());

        List<Specialization> newSpecializations = new ArrayList<>();
        newSpecializations.add(mock(Specialization.class));
        course.setSpecializations(newSpecializations);
        assertEquals(newSpecializations, course.getSpecializations());
    }
}
