package com.crimsonlogic.onlinejobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.onlinejobportal.dto.JobDTO;
import com.crimsonlogic.onlinejobportal.dto.LoginRequestDTO;
import com.crimsonlogic.onlinejobportal.dto.RecruiterDTO;
import com.crimsonlogic.onlinejobportal.enums.EmployeeRange;
import com.crimsonlogic.onlinejobportal.service.JobService;
import com.crimsonlogic.onlinejobportal.service.RecruiterService;

@RestController
@RequestMapping("/api/recruiter")
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;
    
    @Autowired
    private JobService jobService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveRecruiter(
            @RequestParam("fullName") String fullName,
            @RequestParam("officialEmail") String officialEmail,
            @RequestParam("password") String password,
            @RequestParam("companyName") String companyName,
            @RequestParam("employeeRange") EmployeeRange employeeRange,
            @RequestParam("designation") String designation,
            @RequestParam("companyLocation") String companyLocation,
            @RequestParam("companyAddress") String companyAddress,
            @RequestParam("aboutCompany") String aboutCompany,
            @RequestParam("industry") String industry,
            @RequestParam(value = "companyLogo", required = false) MultipartFile companyLogo) {

        // Create a RecruiterDTO
        RecruiterDTO recruiterDTO = new RecruiterDTO();
        recruiterDTO.setFullName(fullName);
        recruiterDTO.setOfficialEmail(officialEmail);
        recruiterDTO.setPassword(password);
        recruiterDTO.setCompanyName(companyName);
        recruiterDTO.setEmployeeRange(employeeRange);
        recruiterDTO.setDesignation(designation);
        recruiterDTO.setCompanyLocation(companyLocation);
        recruiterDTO.setCompanyAddress(companyAddress);
        recruiterDTO.setAboutCompany(aboutCompany);
        recruiterDTO.setIndustry(industry);

        try {
            // Define the directory to store company logos
            String logoDir = "D:/GA_Training_STS_Workspace/onlinejobportal/src/main/resources/static/company_logos/";
            java.io.File logoDirectory = new java.io.File(logoDir);
            if (!logoDirectory.exists()) {
                logoDirectory.mkdirs();  // Create directory if it doesn't exist
            }

            // Save company logo file if provided
            if (companyLogo != null && !companyLogo.isEmpty()) {
                String logoFileName = companyLogo.getOriginalFilename();
                String logoPath = logoDir + logoFileName;
                companyLogo.transferTo(new java.io.File(logoPath));  // Save file to the server
                String logoUrl = "company_logos/" + logoFileName;  // Generate relative URL
                recruiterDTO.setCompanyLogoUrl(logoUrl);  // Set logo URL in the DTO
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving files: " + e.getMessage());
        }

        // Save recruiter details
        recruiterService.saveRecruiter(recruiterDTO);
        return ResponseEntity.ok("Recruiter saved successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginRecruiter(@RequestBody LoginRequestDTO loginRequestDTO) {
        boolean isAuthenticated = recruiterService.loginRecruiter(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    
    @GetMapping("/details")
    public ResponseEntity<?> getRecruiterDetails(@RequestParam("email") String email) {
        RecruiterDTO recruiterDTO = recruiterService.getRecruiterByEmail(email);
        if (recruiterDTO == null) {
            return ResponseEntity.status(404).body("Recruiter not found");
        }
        return ResponseEntity.ok(recruiterDTO); // Return recruiter details if found
    }


    @GetMapping("/jobs")
    public List<JobDTO> getJobsByRecruiterEmail(@RequestParam String recruiterEmail) {
        return jobService.getJobsByRecruiter(recruiterEmail);
    }
}
