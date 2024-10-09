package com.crimsonlogic.onlinejobportal.entity;

import java.util.List;

import com.crimsonlogic.onlinejobportal.enums.EmploymentType;
import com.crimsonlogic.onlinejobportal.enums.WorkMode;
import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * The Job entity represents a job listing posted by a recruiter.
 * Each job is associated with a recruiter and contains details such as job title, location, and required skills.
 * A job can have multiple locations where it is available.
 */
@Entity
@Table(name = "jobs", indexes = {
    @Index(name = "idx_job_title", columnList = "job_title")
})
public class Job {

    /**
     * The unique identifier for each job, generated with the prefix "JOB".
     */
    @Id
    @Column(name = "job_id")
    private String jobId = IDGenerator.generateID("JOB");

    /**
     * The title or designation of the job being posted.
     */
    @Column(name = "job_title")
    private String jobTitle;

    /**
     * The type of employment for the job, such as Full-time or Part-time.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "job_employment_type")
    private EmploymentType employmentType;

    /**
     * A list of skills required for the job.
     * This is a many-to-many relationship linked via the JobSkill entity.
     * A job can require multiple skills.
     */
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<JobSkill> keySkills;

    /**
     * A list of locations where the job is available.
     * This is a one-to-many relationship linked via the JobLocation entity.
     * A job can be posted in multiple locations.
     */
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<JobLocation> jobLocations;

    /**
     * The department under which the job is categorized.
     */
    @Column(name = "job_department")
    private String department;

    /**
     * The category or role associated with the job (e.g., Engineering, Marketing).
     */
	/*
	 * @Column(name = "job_role_category") private String roleCategory;
	 */

    /**
     * The mode of work for the job, such as Work-from-home, In-office, or Hybrid.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "job_work_mode")
    private WorkMode workMode;

    /**
     * The minimum required experience for the job, in years.
     */
    @Column(name = "job_min_experience")
    private Integer minExperience;

    /**
     * The maximum relevant experience for the job, in years.
     */
    @Column(name = "job_max_experience")
    private Integer maxExperience;

    /**
     * The minimum annual salary offered for the job.
     */
    @Column(name = "job_min_salary")
    private Integer minSalary;

    /**
     * The maximum annual salary offered for the job.
     */
    @Column(name = "job_max_salary")
    private Integer maxSalary;

    /**
     * The educational qualification required for the job.
     */
    @Column(name = "job_qualification")
    private String qualification;

    /**
     * The specific course or field of study required for the job.
     */
    @Column(name = "job_course")
    private String course;

    /**
     * The specialization or area of expertise required for the job.
     */
    @Column(name = "job_specialization")
    private String specialization;

    /**
     * The industry experience required for the job (if any).
     */
	/*
	 * @Column(name = "job_candidate_industry") private String candidateIndustry;
	 */

    /**
     * A detailed description of the job.
     * This is stored as a large object (Lob) since job descriptions can be lengthy.
     */
    @Lob
    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    /**
     * The number of open vacancies for the job.
     */
    @Column(name = "job_vacancies")
    private Integer vacancies;

    /**
     * A many-to-one relationship linking the job to the recruiter who posted it.
     */
    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

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

	public List<JobSkill> getKeySkills() {
		return keySkills;
	}

	public void setKeySkills(List<JobSkill> keySkills) {
		this.keySkills = keySkills;
	}

	public List<JobLocation> getJobLocations() {
		return jobLocations;
	}

	public void setJobLocations(List<JobLocation> jobLocations) {
		this.jobLocations = jobLocations;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
    
    
}
