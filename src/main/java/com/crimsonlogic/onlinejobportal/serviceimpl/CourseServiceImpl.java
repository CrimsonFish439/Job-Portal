package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.CourseDTO;
import com.crimsonlogic.onlinejobportal.entity.Course;
import com.crimsonlogic.onlinejobportal.repository.CourseRepository;
import com.crimsonlogic.onlinejobportal.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseDTO> getCoursesByQualification(String qualification) {
        List<Course> courses = courseRepository.findByQualification_QualificationName(qualification);
        return courses.stream()
                .map(course -> {
                    CourseDTO dto = new CourseDTO();
                    dto.setCourseId(course.getCourseId());
                    dto.setCourseName(course.getCourseName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

