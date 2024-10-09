package com.crimsonlogic.onlinejobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	List<Course> findByQualification_QualificationId(Long qualificationId);
    Course findByCourseName(String courseName);
    List<Course> findByQualification_QualificationName(String qualificationName);
}