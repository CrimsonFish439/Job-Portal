package com.crimsonlogic.onlinejobportal.dto;

import com.crimsonlogic.onlinejobportal.enums.EmployeeRange;

public class RecruiterDTO {

    private String fullName;

    private String officialEmail;

    private String password;

    private String companyName;

    private EmployeeRange employeeRange;

    private String designation;

    private String companyLocation;

    private String companyAddress;

    private String aboutCompany;
    
    private String industry;
    
    private String companyLogoUrl;
    

    // Getter and Setter methods

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompanyLogoUrl() {
		return companyLogoUrl;
	}

	public void setCompanyLogoUrl(String companyLogoUrl) {
		this.companyLogoUrl = companyLogoUrl;
	}

}
