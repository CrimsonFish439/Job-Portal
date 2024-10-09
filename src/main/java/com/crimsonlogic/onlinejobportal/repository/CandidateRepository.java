package com.crimsonlogic.onlinejobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.Candidate;
import com.crimsonlogic.onlinejobportal.entity.User;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, String> {
	Candidate findByUser(User user);
	
	@Query("SELECT c FROM Candidate c WHERE c.user.email = :email")
    Candidate findByUserEmail(@Param("email") String email);
	
	@Query("SELECT c FROM Candidate c WHERE c.candidateId IN "
	         + "(SELECT ja.candidate.candidateId FROM JobApplication ja WHERE ja.job.jobId = :jobId)")
	    List<Candidate> findCandidatesByJobId(@Param("jobId") String jobId);


}
