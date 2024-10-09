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

import com.crimsonlogic.onlinejobportal.dto.CourseDTO;
import com.crimsonlogic.onlinejobportal.entity.Course;
import com.crimsonlogic.onlinejobportal.repository.CourseRepository;

class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCoursesByQualification() {
        // Arrange
        Course course1 = new Course();
        course1.setCourseId(1L);
        course1.setCourseName("Computer Science");

        Course course2 = new Course();
        course2.setCourseId(2L);
        course2.setCourseName("Electrical Engineering");

        when(courseRepository.findByQualification_QualificationName("Bachelor's")).thenReturn(Arrays.asList(course1, course2));

        // Act
        List<CourseDTO> result = courseService.getCoursesByQualification("Bachelor's");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Computer Science", result.get(0).getCourseName());
        assertEquals("Electrical Engineering", result.get(1).getCourseName());
    }
}

