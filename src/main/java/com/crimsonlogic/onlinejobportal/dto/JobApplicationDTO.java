package com.crimsonlogic.onlinejobportal.dto;

import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;

public class JobApplicationDTO {
    
    private String jobId;
    private String candidateId;  // Or you can use candidate's email or any other identifier.
    private ApplicationStatus status = ApplicationStatus.APPLIED;  // Default status on application

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}

