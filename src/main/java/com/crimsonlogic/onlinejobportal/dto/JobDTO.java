package com.crimsonlogic.onlinejobportal.dto;

import java.util.List;

import com.crimsonlogic.onlinejobportal.enums.EmploymentType;
import com.crimsonlogic.onlinejobportal.enums.WorkMode;

public class JobDTO {

	private String jobId;
    private String jobTitle;
    private EmploymentType employmentType;
    private List<String> keySkillsIds; // List of skill IDs
    private List<String> jobLocationIds; // List of location IDs
    private String department;
    private WorkMode workMode;
    private Integer minExperience;
    private Integer maxExperience;
    private Integer minSalary;
    private Integer maxSalary;
    private String qualificationName; // Qualification ID from Qualification table
    private String courseName; // Course ID from Course table
    private String specializationName; // Specialization ID from Specialization table
    private String jobDescription;
    private Integer vacancies;
    private String recruiterEmail;
    private RecruiterDTO recruiter;
    // Getters and Setters

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public List<String> getKeySkillsIds() {
        return keySkillsIds;
    }

    public void setKeySkillsIds(List<String> keySkillsIds) {
        this.keySkillsIds = keySkillsIds;
    }

    public List<String> getJobLocationIds() {
        return jobLocationIds;
    }

    public void setJobLocationIds(List<String> jobLocationIds) {
        this.jobLocationIds = jobLocationIds;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public WorkMode getWorkMode() {
        return workMode;
    }

    public void setWorkMode(WorkMode workMode) {
        this.workMode = workMode;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getVacancies() {
        return vacancies;
    }

    public void setVacancies(Integer vacancies) {
        this.vacancies = vacancies;
    }

	public String getQualificationName() {
		return qualificationName;
	}

	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getSpecializationName() {
		return specializationName;
	}

	public void setSpecializationName(String specializationName) {
		this.specializationName = specializationName;
	}

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}

	public RecruiterDTO getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(RecruiterDTO recruiter) {
		this.recruiter = recruiter;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}

