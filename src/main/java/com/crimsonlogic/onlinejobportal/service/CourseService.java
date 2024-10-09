package com.crimsonlogic.onlinejobportal.service;

import java.util.List;

import com.crimsonlogic.onlinejobportal.dto.CourseDTO;

public interface CourseService {
    List<CourseDTO> getCoursesByQualification(String qualification);
}
