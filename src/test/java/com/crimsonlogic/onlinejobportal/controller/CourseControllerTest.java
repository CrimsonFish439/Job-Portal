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

import com.crimsonlogic.onlinejobportal.dto.CourseDTO;
import com.crimsonlogic.onlinejobportal.service.CourseService;

class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    void testGetCoursesByQualification() throws Exception {
        CourseDTO course1 = new CourseDTO();
        course1.setCourseId(1L);
        course1.setCourseName("Computer Science");

        CourseDTO course2 = new CourseDTO();
        course2.setCourseId(2L);
        course2.setCourseName("Data Science");

        List<CourseDTO> courses = Arrays.asList(course1, course2);
        when(courseService.getCoursesByQualification("Bachelor's")).thenReturn(courses);

        mockMvc.perform(get("/api/courses/byqualification/Bachelor's"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].courseName").value("Computer Science"))
                .andExpect(jsonPath("$[1].courseName").value("Data Science"));

        verify(courseService, times(1)).getCoursesByQualification("Bachelor's");
    }

}
