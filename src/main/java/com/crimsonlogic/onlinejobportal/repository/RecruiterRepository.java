package com.crimsonlogic.onlinejobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimsonlogic.onlinejobportal.entity.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, String> {

	Recruiter findByUserEmail(String email);
}
