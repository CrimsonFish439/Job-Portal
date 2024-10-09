package com.crimsonlogic.onlinejobportal.entity;

import com.crimsonlogic.onlinejobportal.enums.EmployeeRange;
import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * The Recruiter entity represents a user who is responsible for posting and managing job listings on behalf of a company.
 * Each recruiter is linked to a User entity for authentication and has additional attributes specific to recruitment.
 */
@Entity
@Table(name = "recruiters")
public class Recruiter {

    /**
     * The unique identifier for the recruiter, generated with the prefix "RCT".
     */
    @Id
    @Column(name = "recruiter_id")
    private String recruiterId = IDGenerator.generateID("RCT");

    /**
     * A one-to-one relationship with the User entity.
     * This links the recruiter with their User account for authentication.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The full name of the recruiter.
     */
    @Column(name = "recruiter_full_name")
    private String fullName;

    /**
     * The official email of the recruiter, used for communication purposes.
     * This field is unique across all recruiters.
     */
    @Column(name = "recruiter_official_email", unique = true)
    private String officialEmail;

    /**
     * The name of the company the recruiter represents.
     */
    @Column(name = "recruiter_company_name")
    private String companyName;

    /**
     * A many-to-one relationship with the Industry entity, representing the industry the recruiter's company belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    /**
     * The employee range of the recruiter's company (e.g., 1-10 employees, 11-50 employees).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "recruiter_employee_range")
    private EmployeeRange employeeRange;

    /**
     * The designation of the recruiter within the company.
     */
    @Column(name = "recruiter_designation")
    private String designation;

    /**
     * The location of the recruiter's company.
     */
    @Column(name = "recruiter_company_location")
    private String companyLocation;

    /**
     * The address of the recruiter's company.
     */
    @Column(name = "recruiter_company_address")
    private String companyAddress;

    /**
     * A brief description of the recruiter's company.
     */
    //@Lob
	@Column(name = "recruiter_about_company"/* , columnDefinition = "TEXT" */)
    private String aboutCompany;

    /**
     * The URL to the company's logo.
     */
    @Column(name = "recruiter_company_logo_url")
    private String companyLogoUrl;

	public String getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOfficialEmail() {
		return officialEmail;
	}

	public void setOfficialEmail(String officialEmail) {
		this.officialEmail = officialEmail;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public EmployeeRange getEmployeeRange() {
		return employeeRange;
	}

	public void setEmployeeRange(EmployeeRange employeeRange) {
		this.employeeRange = employeeRange;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompanyLocation() {
		return companyLocation;
	}

	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getAboutCompany() {
		return aboutCompany;
	}

	public void setAboutCompany(String aboutCompany) {
		this.aboutCompany = aboutCompany;
	}

	public String getCompanyLogoUrl() {
		return companyLogoUrl;
	}

	public void setCompanyLogoUrl(String companyLogoUrl) {
		this.companyLogoUrl = companyLogoUrl;
	}
    
    
}
