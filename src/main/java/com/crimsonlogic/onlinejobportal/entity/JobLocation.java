package com.crimsonlogic.onlinejobportal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The JobLocation entity represents the locations where a specific job is available.
 * It establishes a many-to-one relationship between the Job and Location entities.
 * A job can have multiple locations, and a location can be associated with multiple jobs.
 */
@Entity
@Table(name = "job_locations")
public class JobLocation {

    /**
     * The unique identifier for each JobLocation record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * A many-to-one relationship linking the JobLocation to a specific job.
     * Each job can have multiple JobLocation entries.
     */
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    /**
     * A many-to-one relationship linking the JobLocation to a specific location.
     * Each location can be associated with multiple jobs.
     */
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    
    
}
