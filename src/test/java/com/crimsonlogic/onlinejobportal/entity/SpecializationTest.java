package com.crimsonlogic.onlinejobportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecializationTest {

    private Specialization specialization;
    private Course mockCourse;

    @BeforeEach
    void setUp() {
        specialization = new Specialization();
        mockCourse = mock(Course.class);

        specialization.setSpecializationId(1L);
        specialization.setSpecializationName("Data Science");
        specialization.setCourse(mockCourse);
    }

    @Test
    void testGetters() {
        assertEquals(1L, specialization.getSpecializationId());
        assertEquals("Data Science", specialization.getSpecializationName());
        assertEquals(mockCourse, specialization.getCourse());
    }

    @Test
    void testSetters() {
        Course newCourse = mock(Course.class);
        specialization.setSpecializationId(2L);
        specialization.setSpecializationName("Machine Learning");
        specialization.setCourse(newCourse);

        assertEquals(2L, specialization.getSpecializationId());
        assertEquals("Machine Learning", specialization.getSpecializationName());
        assertEquals(newCourse, specialization.getCourse());
    }
}
