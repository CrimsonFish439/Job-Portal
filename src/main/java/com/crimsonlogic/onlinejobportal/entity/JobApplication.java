package com.crimsonlogic.onlinejobportal.entity;

import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;
import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The JobApplication entity represents an application submitted by a candidate for a specific job.
 * It tracks the status of the application, such as Applied, Shortlisted, or Rejected.
 */
@Entity
@Table(name = "job_applications")
public class JobApplication {

    /**
     * The unique identifier for the job application, generated with the prefix "APP".
     */
    @Id
    @Column(name = "application_id")
    private String applicationId = IDGenerator.generateID("APP");

    /**
     * A many-to-one relationship linking the application to the candidate who submitted it.
     */
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    /**
     * A many-to-one relationship linking the application to the job being applied for.
     */
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    /**
     * The status of the application, such as Applied, Shortlisted, Selected, or Rejected.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    private ApplicationStatus status;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
}
