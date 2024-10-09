package com.crimsonlogic.onlinejobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.onlinejobportal.dto.CourseDTO;
import com.crimsonlogic.onlinejobportal.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/byqualification/{qualification}")
    public ResponseEntity<List<CourseDTO>> getCoursesByQualification(@PathVariable String qualification) {
        List<CourseDTO> courses = courseService.getCoursesByQualification(qualification);
        return ResponseEntity.ok(courses);
    }
}
