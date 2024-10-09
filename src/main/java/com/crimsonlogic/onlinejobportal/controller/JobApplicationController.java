package com.crimsonlogic.onlinejobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.onlinejobportal.dto.JobApplicationDTO;
import com.crimsonlogic.onlinejobportal.enums.ApplicationStatus;
import com.crimsonlogic.onlinejobportal.service.JobApplicationService;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/apply")
    public ResponseEntity<String> applyForJob(@RequestBody JobApplicationDTO jobApplicationDTO) {
        if (jobApplicationDTO.getCandidateId() == null || jobApplicationDTO.getJobId() == null) {
            throw new IllegalArgumentException("Candidate ID and Job ID are required.");
        }

        jobApplicationService.applyForJob(jobApplicationDTO);
        return ResponseEntity.ok("Job application submitted successfully");
    }
    
    @PostMapping("/{candidateId}/job/{jobId}/shortlist")
    public ResponseEntity<?> shortlistCandidate(@PathVariable String candidateId, @PathVariable String jobId) {
        jobApplicationService.updateApplicationStatus(candidateId, jobId, ApplicationStatus.SHORTLISTED);
        return ResponseEntity.ok("Candidate shortlisted");
    }

    @PostMapping("/{candidateId}/job/{jobId}/reject")
    public ResponseEntity<?> rejectCandidate(@PathVariable String candidateId, @PathVariable String jobId) {
        jobApplicationService.updateApplicationStatus(candidateId, jobId, ApplicationStatus.REJECTED);
        return ResponseEntity.ok("Candidate rejected");
    }

    @PostMapping("/{candidateId}/job/{jobId}/accept")
    public ResponseEntity<?> acceptCandidate(@PathVariable String candidateId, @PathVariable String jobId) {
        jobApplicationService.updateApplicationStatus(candidateId, jobId, ApplicationStatus.SELECTED);
        return ResponseEntity.ok("Candidate accepted");
    }
    
    @GetMapping("/candidate/{candidateId}/applications")
    public ResponseEntity<List<JobApplicationDTO>> getCandidateApplications(@PathVariable String candidateId) {
        List<JobApplicationDTO> applications = jobApplicationService.getApplicationsByCandidateId(candidateId);
        return ResponseEntity.ok(applications);
    }


}

