package com.crimsonlogic.onlinejobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.JobDTO;

@Service
public interface JobService {

	void saveJob(JobDTO jobDTO);

	List<JobDTO> getJobsByRecruiter(String recruiterEmail);

	List<JobDTO> getAllJobs();

	JobDTO getJobById(String jobId);

	List<CandidateDTO> getCandidatesByJobId(String jobId);

	List<JobDTO> getJobsByLocation(String location);

	List<JobDTO> getJobsByTitle(String title);

	List<String> getAllCompanies();

	List<JobDTO> getRecommendedJobsForCandidate(String candidateId);

	List<JobDTO> getJobsByCompanies(List<String> companies);

}
