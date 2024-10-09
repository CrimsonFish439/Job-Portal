package com.crimsonlogic.onlinejobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, String> {

    // Find all job applications by candidate ID
    List<JobApplication> findByCandidateCandidateId(String candidateId);

    // Find all job applications for a specific job
    List<JobApplication> findByJobJobId(String jobId);

    // Find a specific job application by candidate and job
    @Query("SELECT ja FROM JobApplication ja WHERE ja.candidate.candidateId = :candidateId AND ja.job.jobId = :jobId")
    JobApplication findByCandidateCandidateIdAndJobJobId(@Param("candidateId") String candidateId, @Param("jobId") String jobId);

}
