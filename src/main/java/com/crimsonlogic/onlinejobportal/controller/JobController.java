	package com.crimsonlogic.onlinejobportal.controller;
	
	import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.JobDTO;
import com.crimsonlogic.onlinejobportal.service.JobService;
	
	@RestController
	@RequestMapping("/api/job")
	public class JobController {
	
	    @Autowired
	    private JobService jobService;
	
	    @PostMapping("/postjob")
	    public ResponseEntity<String> postJob(@RequestBody JobDTO jobDTO) {
	        jobService.saveJob(jobDTO);
	        return ResponseEntity.ok("Job posted successfully!");
	    }
	    
	    @GetMapping("/recruiter-jobs")
	    public ResponseEntity<List<JobDTO>> getJobsByRecruiter(@RequestParam("recruiterEmail") String recruiterEmail) {
	        List<JobDTO> jobs = jobService.getJobsByRecruiter(recruiterEmail);
	        return ResponseEntity.ok(jobs);
	    }
	    
	    @GetMapping("/all-jobs")
	    public ResponseEntity<List<JobDTO>> getAllJobs(
	        @RequestParam(value = "location", required = false) String location,
	        @RequestParam(value = "title", required = false) String title,
	        @RequestParam(value = "companies", required = false) List<String> companies) {
	
	        List<JobDTO> jobs;
	
	        if (title != null && !title.isEmpty()) {
	            jobs = jobService.getJobsByTitle(title); // Search by job title/designation
	        } else if (location != null && !location.isEmpty()) {
	            jobs = jobService.getJobsByLocation(location); // Search by location
	        } else if (companies != null && !companies.isEmpty()) {
	            jobs = jobService.getJobsByCompanies(companies); // Search by companies
	        } else {
	            jobs = jobService.getAllJobs(); // Get all jobs if no filters
	        }
	
	        return ResponseEntity.ok(jobs);
	    }
	
	    
	    @GetMapping("/job-details")
	    public ResponseEntity<JobDTO> getJobById(@RequestParam String jobId) {
	        JobDTO jobDTO = jobService.getJobById(jobId);
	        return ResponseEntity.ok(jobDTO);
	    }
	
	    @GetMapping("/candidates")
	    public ResponseEntity<List<CandidateDTO>> getCandidatesByJobId(@RequestParam("jobId") String jobId) {
	        List<CandidateDTO> candidates = jobService.getCandidatesByJobId(jobId);
	        return ResponseEntity.ok(candidates);
	    }
	    
	    @GetMapping("/all-companies")
	    public ResponseEntity<List<String>> getAllCompanies() {
	        List<String> companies = jobService.getAllCompanies();
	        return ResponseEntity.ok(companies);
	    }
	
	}
