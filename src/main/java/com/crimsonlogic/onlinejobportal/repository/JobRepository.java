package com.crimsonlogic.onlinejobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crimsonlogic.onlinejobportal.entity.Job;
import com.crimsonlogic.onlinejobportal.entity.Recruiter;

public interface JobRepository extends JpaRepository<Job, String> {

	List<Job> findByRecruiter(Recruiter recruiter);
	
	@Query("SELECT j FROM Job j JOIN j.jobLocations jl WHERE jl.location.locationName = :location")
    List<Job> findByLocation(@Param("location") String location);
	
	List<Job> findByJobTitleContainingIgnoreCase(String title);

	@Query("SELECT DISTINCT j.recruiter.companyName FROM Job j WHERE j.recruiter.companyName IS NOT NULL")
	List<String> findAllCompanies();
	
	@Query("SELECT j FROM Job j WHERE j.recruiter.companyName IN :companies")
	List<Job> findByCompanyNamesIn(@Param("companies") List<String> companies);



}
