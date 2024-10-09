package com.crimsonlogic.onlinejobportal.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.CandidateProfileDTO;
import com.crimsonlogic.onlinejobportal.dto.LoginRequestDTO;
import com.crimsonlogic.onlinejobportal.entity.JobApplication;
import com.crimsonlogic.onlinejobportal.enums.WorkStatus;
import com.crimsonlogic.onlinejobportal.service.CandidateService;
import com.crimsonlogic.onlinejobportal.service.JobApplicationService;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    
    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveCandidate(
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("workStatus") String workStatus,
            @RequestParam("currentLocation") String currentLocation,
            @RequestParam("workExperienceYears") String workExperienceYears,
            @RequestParam("annualSalary") String annualSalary,
            @RequestParam("highestQualification") String highestQualification,
            @RequestParam("selectedCourse") String selectedCourse,
            @RequestParam("selectedSpecialization") String selectedSpecialization,
            @RequestParam("selectedUniversity") String selectedUniversity,
            @RequestParam("selectedSkills") List<String> selectedSkills,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("dateOfBirth") String dateOfBirth,
            @RequestParam("gender") String gender,
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("profileDescription") String profileDescription) {

        // Create a CandidateDTO to collect all the data
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setFullName(fullName);
        candidateDTO.setEmail(email);
        candidateDTO.setPassword(password);
        candidateDTO.setMobileNumber(mobileNumber);
        candidateDTO.setWorkStatus(WorkStatus.valueOf(workStatus));
        candidateDTO.setCurrentLocation(currentLocation);
        candidateDTO.setWorkExperienceYears(Integer.parseInt(workExperienceYears));
        candidateDTO.setAnnualSalary(new BigDecimal(annualSalary));
        candidateDTO.setHighestQualification(highestQualification);
        candidateDTO.setCourse(selectedCourse);
        candidateDTO.setSpecialization(selectedSpecialization);
        candidateDTO.setUniversity(selectedUniversity);
        candidateDTO.setSelectedSkills(selectedSkills);
        candidateDTO.setDateOfBirth(LocalDate.parse(dateOfBirth));
        candidateDTO.setGender(gender);
        candidateDTO.setProfileSummary(profileDescription);

        try {
            // Define the directories for storing profile pictures and resumes
            String pictureDir = "D:/GA_Training_STS_Workspace/onlinejobportal/src/main/resources/static/profile_pictures/";
            String resumeDir = "D:/GA_Training_STS_Workspace/onlinejobportal/src/main/resources/static/resumes/";

            // Create the directories if they don't exist
            java.io.File pictureDirectory = new java.io.File(pictureDir);
            java.io.File resumeDirectory = new java.io.File(resumeDir);

            if (!pictureDirectory.exists()) {
                pictureDirectory.mkdirs();
            }
            if (!resumeDirectory.exists()) {
                resumeDirectory.mkdirs();
            }

            // Save the profile picture
            String pictureFileName = picture.getOriginalFilename();
            String picturePath = pictureDir + pictureFileName;
            picture.transferTo(new java.io.File(picturePath));
            String pictureUrl = "profile_pictures/" + pictureFileName;  // Relative URL for serving
            candidateDTO.setProfilePictureUrl(pictureUrl);

            // Save the resume
            String resumeFileName = resume.getOriginalFilename();
            String resumePath = resumeDir + resumeFileName;
            resume.transferTo(new java.io.File(resumePath));
            String resumeUrl = "resumes/" + resumeFileName;  // Relative URL for serving
            candidateDTO.setResumeUrl(resumeUrl);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving files: " + e.getMessage());
        }

        // Save candidate details
        candidateService.saveCandidate(candidateDTO);
        return ResponseEntity.ok("Candidate saved successfully!");
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginCandidate(@RequestBody LoginRequestDTO loginRequest) {
        boolean isAuthenticated = candidateService.loginCandidate(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            // Fetch candidate details after authentication
            CandidateDTO candidateDTO = candidateService.getCandidateByEmail(loginRequest.getEmail());

            // Return candidateId along with success message
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("candidateId", candidateDTO.getCandidateId());
            System.out.println(candidateDTO.getCandidateId());// Return candidateId
            response.put("email", candidateDTO.getEmail());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }



    @GetMapping("/details")
    public ResponseEntity<?> getCandidateDetails(@RequestParam("email") String email) {
        try {
            CandidateDTO candidateDTO = candidateService.getCandidateByEmail(email);
            return ResponseEntity.ok(candidateDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching candidate details: " + e.getMessage());
        }
    }

    @PostMapping("/changeEmail")
    public ResponseEntity<String> changeEmail(@RequestBody Map<String, String> request) {
        String currentEmail = request.get("currentEmail");
        String newEmail = request.get("newEmail");
        boolean isUpdated = candidateService.updateEmail(currentEmail, newEmail);

        if (isUpdated) {
            return ResponseEntity.ok("Email updated successfully.");
        } else {
            return ResponseEntity.status(400).body("Error updating email.");
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");

        boolean isUpdated = candidateService.updatePassword(email, currentPassword, newPassword);

        if (isUpdated) {
            return ResponseEntity.ok("Password updated successfully.");
        } else {
            return ResponseEntity.status(400).body("Error updating password.");
        }
    }


    
    @PostMapping(value = "/updateProfilePicture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfilePicture(@RequestParam("email") String email, @RequestParam("picture") MultipartFile picture) {
        try {
            String profilePicUrl = candidateService.updateProfilePicture(email, picture);
            return ResponseEntity.ok("Profile picture updated successfully: " + profilePicUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating profile picture: " + e.getMessage());
        }
    }

    
    @PostMapping("/deleteProfilePicture")
    public ResponseEntity<?> deleteProfilePicture(@RequestParam("email") String email) {
        try {
            candidateService.deleteProfilePicture(email);
            return ResponseEntity.ok("Profile picture deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting profile picture: " + e.getMessage());
        }
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable String candidateId) {
        CandidateDTO candidateDTO = candidateService.getCandidateById(candidateId);
        return ResponseEntity.ok(candidateDTO);
    }
    
    @GetMapping("/{candidateId}/job/{jobId}")
    public ResponseEntity<CandidateProfileDTO> getCandidateProfileWithStatus(@PathVariable String candidateId, @PathVariable String jobId) {
        CandidateDTO candidateDTO = candidateService.getCandidateById(candidateId);
        JobApplication jobApplication = jobApplicationService.getApplicationByCandidateAndJob(candidateId, jobId);

        if (jobApplication == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Create and return the combined response DTO
        CandidateProfileDTO candidateProfileDTO = new CandidateProfileDTO();
        candidateProfileDTO.setCandidate(candidateDTO);
        candidateProfileDTO.setStatus(jobApplication.getStatus());

        return ResponseEntity.ok(candidateProfileDTO);
    }
    
}
