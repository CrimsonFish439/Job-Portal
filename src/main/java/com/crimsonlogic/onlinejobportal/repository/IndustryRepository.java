package com.crimsonlogic.onlinejobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.Industry;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, String> {

	Optional<Industry> findByIndustryName(String industryName);
	
}
