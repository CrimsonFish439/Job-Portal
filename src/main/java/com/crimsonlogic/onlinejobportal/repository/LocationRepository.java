package com.crimsonlogic.onlinejobportal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
    List<Location> findAll();
    
    Optional<Location> findByLocationId(String locationId);
    
    
}
