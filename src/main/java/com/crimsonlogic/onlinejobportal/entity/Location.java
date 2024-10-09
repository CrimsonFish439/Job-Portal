package com.crimsonlogic.onlinejobportal.entity;

import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Location entity represents a geographical location where jobs may be available.
 * Locations are used in conjunction with the JobLocation entity to represent job availability by location.
 * Each location can be associated with multiple jobs.
 */
@Entity
@Table(name = "locations")
public class Location {

    /**
     * The unique identifier for each location, generated with the prefix "LOC".
     */
    @Id
    @Column(name = "location_id")
    private String locationId = IDGenerator.generateID("LOC");

    /**
     * The name of the location (e.g., city, region).
     * This field is unique to avoid duplicate location names.
     */
    @Column(name = "location_name", unique = true)
    private String locationName;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
    
    
}
