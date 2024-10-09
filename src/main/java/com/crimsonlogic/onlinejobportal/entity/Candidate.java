package com.crimsonlogic.onlinejobportal.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.crimsonlogic.onlinejobportal.enums.WorkStatus;
import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The Candidate entity represents a user who is looking for jobs.
 * Each candidate is associated with a User entity for authentication and has additional attributes specific to job-seeking.
 * A candidate can possess multiple skills.
 */
@Entity
@Table(name = "candidates")
public class Candidate {

    /**
     * The unique identifier for the candidate, generated with the prefix "CND".
     */
    @Id
    @Column(name = "candidate_id")
    private String candidateId = IDGenerator.generateID("CND");

    /**
     * A one-to-one relationship with the User entity.
     * This links the candidate with their User account for authentication.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    /**
     * The full name of the candidate.
     */
    @Column(name = "candidate_full_name")
    private String fullName;

    /**
     * The mobile number of the candidate.
     */
    @Column(name = "candidate_mobile_number")
    private String mobileNumber;

    /**
     * The gender of the candidate.
     */
    @Column(name = "candidate_gender")
    private String gender;

    /**
     * The date of birth of the candidate.
     */
    @Column(name = "candidate_date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * The current work status of the candidate, such as Employed, Unemployed, or Student.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "candidate_work_status")
    private WorkStatus workStatus;

    /**
     * The current location of the candidate.
     */
    @Column(name = "candidate_current_location")
    private String currentLocation;

    /**
     * The highest qualification attained by the candidate.
     */
    @Column(name = "candidate_highest_qualification")
    private String highestQualification;

    /**
     * The course the candidate studied.
     */
    @Column(name = "candidate_course")
    private String course;

    /**
     * The specialization the candidate studied.
     */
    @Column(name = "candidate_specialization")
    private String specialization;

    /**
     * The name of the university or institution where the candidate studied.
     */
    @Column(name = "candidate_university")
    private String university;

    /**
     * A list of skills possessed by the candidate.
     * This is a many-to-many relationship linked via the CandidateSkill entity.
     * A candidate can possess multiple skills.
     */
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    private List<CandidateSkill> keySkills;
    
    @Column(name = "work_experience_years")
    private Integer workExperienceYears;

    @Column(name = "annual_salary", precision = 15, scale = 2)
    private BigDecimal annualSalary;

    /**
     * A brief summary of the candidate's profile.
     */
    @Column(name = "candidate_profile_summary")
    private String profileSummary;

    /**
     * The URL to the candidate's resume.
     */
    @Column(name = "candidate_resume_url")
    private String resumeUrl;

    /**
     * The URL to the candidate's profile picture.
     */
    @Column(name = "candidate_pfp_url")
    private String profilePictureUrl;
    

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public WorkStatus getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(WorkStatus workStatus) {
		this.workStatus = workStatus;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public List<CandidateSkill> getKeySkills() {
		return keySkills;
	}

	public void setKeySkills(List<CandidateSkill> keySkills) {
		this.keySkills = keySkills;
	}

	public String getProfileSummary() {
		return profileSummary;
	}

	public void setProfileSummary(String profileSummary) {
		this.profileSummary = profileSummary;
	}

	public String getResumeUrl() {
		return resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}
    
	public Integer getWorkExperienceYears() {
        return workExperienceYears;
    }

    public void setWorkExperienceYears(Integer workExperienceYears) {
        this.workExperienceYears = workExperienceYears;
    }

    public BigDecimal getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
    }
    
    
}
