package com.crimsonlogic.onlinejobportal.dto;

import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;

public class CandidateProfileDTO {
    private CandidateDTO candidate;
    private ApplicationStatus status;

    // Getters and setters
    public CandidateDTO getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}

