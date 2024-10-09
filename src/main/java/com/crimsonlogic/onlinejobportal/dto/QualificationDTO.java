package com.crimsonlogic.onlinejobportal.dto;

import java.util.List;

import com.crimsonlogic.onlinejobportal.entity.Course;

public class QualificationDTO {
	private Long qualificationId;
    private String qualificationName;
    private List<Course> courses;
	public Long getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
    
    
}
