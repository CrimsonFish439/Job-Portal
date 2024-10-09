package com.crimsonlogic.onlinejobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findAll();
}
