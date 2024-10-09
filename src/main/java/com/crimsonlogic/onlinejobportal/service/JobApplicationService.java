package com.crimsonlogic.onlinejobportal.service;

import java.util.List;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.JobApplicationDTO;
import com.crimsonlogic.onlinejobportal.entity.JobApplication;
import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;

public interface JobApplicationService {

	void applyForJob(JobApplicationDTO jobApplicationDTO);

	List<CandidateDTO> getCandidatesByJobId(String jobId);

	void updateApplicationStatus(String candidateId, String jobId, ApplicationStatus shortlisted);

	JobApplication getApplicationByCandidateAndJob(String candidateId, String jobId);

	List<JobApplicationDTO> getApplicationsByCandidateId(String candidateId);

}
