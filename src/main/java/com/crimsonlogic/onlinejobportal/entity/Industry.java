package com.crimsonlogic.onlinejobportal.entity;

import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The Industry entity represents a category or sector that a company or job belongs to.
 * Each recruiter is associated with a specific industry.
 */
@Entity
@Table(name = "industries")
public class Industry {

    /**
     * The unique identifier for each industry, generated with the prefix "IND".
     */
    @Id
    @Column(name = "industry_id")
    private String industryId = IDGenerator.generateID("IND");

    /**
     * The name of the industry.
     * This field is unique and ensures that no two industries share the same name.
     */
    @Column(name = "industry_name", unique = true)
    private String industryName;

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
    
    
}
