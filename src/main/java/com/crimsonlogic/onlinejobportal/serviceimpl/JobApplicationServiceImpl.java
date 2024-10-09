package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.JobApplicationDTO;
import com.crimsonlogic.onlinejobportal.entity.Candidate;
import com.crimsonlogic.onlinejobportal.entity.Job;
import com.crimsonlogic.onlinejobportal.entity.JobApplication;
import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;
import com.crimsonlogic.onlinejobportal.repository.CandidateRepository;
import com.crimsonlogic.onlinejobportal.repository.JobApplicationRepository;
import com.crimsonlogic.onlinejobportal.repository.JobRepository;
import com.crimsonlogic.onlinejobportal.service.JobApplicationService;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private ModelMapper modelMapper; 

    @Transactional
    @Override
    public void applyForJob(JobApplicationDTO jobApplicationDTO) {
        Job job = jobRepository.findById(jobApplicationDTO.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobApplicationDTO.getJobId()));

        Candidate candidate = candidateRepository.findById(jobApplicationDTO.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + jobApplicationDTO.getCandidateId()));

        // Check if the candidate has already applied for the job
        boolean hasAlreadyApplied = jobApplicationRepository
                .findByCandidateCandidateIdAndJobJobId(candidate.getCandidateId(), job.getJobId()) != null;

        if (hasAlreadyApplied) {
        	throw new ResponseStatusException(HttpStatus.CONFLICT, "You have already applied for this job.");
        }

        // If not applied, create the job application
        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(job);
        jobApplication.setCandidate(candidate);
        jobApplication.setStatus(ApplicationStatus.APPLIED);  // Default status on application

        jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<CandidateDTO> getCandidatesByJobId(String jobId) {
        List<JobApplication> jobApplications = jobApplicationRepository.findByJobJobId(jobId);
        
        // Convert JobApplication to CandidateDTO
        return jobApplications.stream()
            .map(jobApplication -> {
                Candidate candidate = jobApplication.getCandidate();
                CandidateDTO candidateDTO = new CandidateDTO();
                candidateDTO.setFullName(candidate.getFullName());
                candidateDTO.setEmail(candidate.getUser().getEmail());
                candidateDTO.setMobileNumber(candidate.getMobileNumber());
                // Add other fields as needed
                return candidateDTO;
            })
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateApplicationStatus(String candidateId, String jobId, ApplicationStatus status) {
    	System.out.println("Finding Job Application with Candidate ID: " + candidateId + " and Job ID: " + jobId);
    	JobApplication jobApplication = jobApplicationRepository.findByCandidateCandidateIdAndJobJobId(candidateId, jobId);

    	if (jobApplication == null) {
    	    throw new IllegalArgumentException("Job application not found for candidateId: " + candidateId + " and jobId: " + jobId);
    	}


        // Update the candidate's application status
        jobApplication.setStatus(status);
        jobApplicationRepository.save(jobApplication);

        // If the candidate is accepted, reduce job vacancies
        if (status == ApplicationStatus.SELECTED) {
            Job job = jobApplication.getJob();
            if (job.getVacancies() > 0) {
                job.setVacancies(job.getVacancies() - 1);
                jobRepository.save(job);
            }
        }
    }

    @Transactional
    @Override
    public JobApplication getApplicationByCandidateAndJob(String candidateId, String jobId) {
        // Use the repository to find the application
        return jobApplicationRepository.findByCandidateCandidateIdAndJobJobId(candidateId, jobId);
    }

    @Transactional
    @Override
    public List<JobApplicationDTO> getApplicationsByCandidateId(String candidateId) {
        List<JobApplication> applications = jobApplicationRepository.findByCandidateCandidateId(candidateId);
        
        return applications.stream()
                .map(application -> modelMapper.map(application, JobApplicationDTO.class))  // Use ModelMapper
                .collect(Collectors.toList());
    }

}

