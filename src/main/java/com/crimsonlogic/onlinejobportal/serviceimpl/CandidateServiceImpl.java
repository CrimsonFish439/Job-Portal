package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.entity.Candidate;
import com.crimsonlogic.onlinejobportal.entity.CandidateSkill;
import com.crimsonlogic.onlinejobportal.entity.Role;
import com.crimsonlogic.onlinejobportal.entity.Skill;
import com.crimsonlogic.onlinejobportal.entity.User;
import com.crimsonlogic.onlinejobportal.exception.DuplicateEmailException;
import com.crimsonlogic.onlinejobportal.repository.CandidateRepository;
import com.crimsonlogic.onlinejobportal.repository.RoleRepository;
import com.crimsonlogic.onlinejobportal.repository.SkillRepository;
import com.crimsonlogic.onlinejobportal.repository.UserRepository;
import com.crimsonlogic.onlinejobportal.service.CandidateService;
import com.crimsonlogic.onlinejobportal.util.IDGenerator;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    @Transactional
    public void saveCandidate(CandidateDTO candidateDTO) {
    	
    	if (userRepository.findByEmail(candidateDTO.getEmail()) != null) {
            throw new DuplicateEmailException("Email '" + candidateDTO.getEmail() + "' is already registered.");
        }
    	
        // Step 1: Create a new User entity
        User user = new User();
        user.setUserId(IDGenerator.generateID("USR"));  // Generate a unique User ID
        user.setEmail(candidateDTO.getEmail());
        user.setPassword(candidateDTO.getPassword());  // You should hash/encrypt the password in a real application
        user.setStatus(true);  // Active by default

        // Step 2: Fetch and set the Candidate role from the Role table
        Role candidateRole = roleRepository.findByRoleName("CANDIDATE");
        if (candidateRole != null) {
            user.setRole(candidateRole);  // Set the user role as CANDIDATE
        } else {
            throw new RuntimeException("Candidate role not found.");
        }

        // Step 3: Save the User entity to the database
        userRepository.save(user);

        // Step 4: Create and populate the Candidate entity
        Candidate candidate = new Candidate();
        candidate.setCandidateId(IDGenerator.generateID("CND"));  // Generate a unique Candidate ID
        candidate.setUser(user);  // Link the Candidate to the User

        // Set other fields for the Candidate entity
        candidate.setFullName(candidateDTO.getFullName());
        candidate.setMobileNumber(candidateDTO.getMobileNumber());
        candidate.setDateOfBirth(candidateDTO.getDateOfBirth());
        candidate.setGender(candidateDTO.getGender());
        candidate.setWorkStatus(candidateDTO.getWorkStatus());
        candidate.setCurrentLocation(candidateDTO.getCurrentLocation());
        candidate.setHighestQualification(candidateDTO.getHighestQualification());
        candidate.setCourse(candidateDTO.getCourse());
        candidate.setSpecialization(candidateDTO.getSpecialization());
        candidate.setUniversity(candidateDTO.getUniversity());
        candidate.setWorkExperienceYears(candidateDTO.getWorkExperienceYears());
        candidate.setAnnualSalary(candidateDTO.getAnnualSalary());
        candidate.setResumeUrl(candidateDTO.getResumeUrl());
        candidate.setProfilePictureUrl(candidateDTO.getProfilePictureUrl());
        candidate.setProfileSummary(candidateDTO.getProfileSummary());
        

        // Step 5: Handle the selected skills and map them to CandidateSkill
        List<CandidateSkill> candidateSkills = candidateDTO.getSelectedSkills().stream().map(skillId -> {
            Skill skill = skillRepository.findById(skillId)
                    .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + skillId));  // Find the skill by ID
            CandidateSkill candidateSkill = new CandidateSkill();
            candidateSkill.setCandidate(candidate);  // Set the candidate
            candidateSkill.setSkill(skill);  // Set the skill
            return candidateSkill;
        }).collect(Collectors.toList());


        // Set the candidate's skills
        candidate.setKeySkills(candidateSkills);

        // Step 6: Save the Candidate entity along with the CandidateSkills
        candidateRepository.save(candidate);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean loginCandidate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional(readOnly = true)
    public String getCandidateName(String userEmail) {
        // Find user by email
    	var user = userRepository.findByEmail(userEmail);
    	if (user != null) {
    	    Candidate candidate = candidateRepository.findByUser(user);
    	    if (candidate != null) {
    	        System.out.println("Candidate found: " + candidate.getFullName());
    	        return candidate.getFullName();
    	    } else {
    	        System.out.println("No candidate found for user.");
    	    }
    	}
		return "Candidate Not Found";

    }

    @Override
    @Transactional(readOnly = true)
    public String getProfilePicUrl(String userEmail) {
        // Find user by email
        User user = userRepository.findByEmail(userEmail);
        if (user != null) {
            // Find the candidate associated with this user
            Candidate candidate = candidateRepository.findByUser(user);
            if (candidate != null) {
                return candidate.getProfilePictureUrl(); // Return the profile picture URL
            }
        }
        return "User not found";
    }

    @Override
    @Transactional
    public void deleteProfilePicture(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user != null) {
            Candidate candidate = candidateRepository.findByUser(user);
            if (candidate != null) {
                candidate.setProfilePictureUrl(null);
                candidateRepository.save(candidate);
            }
        }
    }

    @Override
    @Transactional
    public String updateProfilePicture(String userEmail, MultipartFile picture) throws IOException {
        User user = userRepository.findByEmail(userEmail);
        if (user != null) {
            Candidate candidate = candidateRepository.findByUser(user);
            if (candidate != null) {
                String pictureDir = "D:/GA_Training_STS_Workspace/onlinejobportal/src/main/resources/static/profile_pictures/";
                java.io.File pictureDirectory = new java.io.File(pictureDir);
                if (!pictureDirectory.exists()) {
                    pictureDirectory.mkdirs();
                }

                String picturePath = pictureDir + picture.getOriginalFilename();
                picture.transferTo(new java.io.File(picturePath));

                candidate.setProfilePictureUrl("profile_pictures/" + picture.getOriginalFilename());
                candidateRepository.save(candidate);

                return candidate.getProfilePictureUrl();
            }
        }
        return null;
    }

    @Override
    public CandidateDTO getCandidateByEmail(String email) {
        // Use the new repository method to find candidate by user email
        Candidate candidate = candidateRepository.findByUserEmail(email);
        
        if (candidate == null) {
            throw new RuntimeException("Candidate not found with email: " + email);
        }
        
        // Map Candidate entity to CandidateDTO
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateId(candidate.getCandidateId());
        candidateDTO.setFullName(candidate.getFullName());
        candidateDTO.setProfilePictureUrl(candidate.getProfilePictureUrl());
        candidateDTO.setMobileNumber(candidate.getMobileNumber());
        candidateDTO.setEmail(candidate.getUser().getEmail());
        candidateDTO.setCurrentLocation(candidate.getCurrentLocation());
        candidateDTO.setWorkExperienceYears(candidate.getWorkExperienceYears());
        candidateDTO.setWorkStatus(candidate.getWorkStatus());
        candidateDTO.setAnnualSalary(candidate.getAnnualSalary());
        
        return candidateDTO;
    }
    
    @Override
    @Transactional
    public boolean updateEmail(String currentEmail, String newEmail) {
        // Find the user by current email
        User user = userRepository.findByEmail(currentEmail);
        
        if (user == null) {
            // User with current email does not exist
            return false;
        }

        // Check if the new email is already in use by another user
        if (userRepository.findByEmail(newEmail) != null) {
            throw new IllegalArgumentException("New email is already in use.");
        }

        // Update the email
        user.setEmail(newEmail);
        userRepository.save(user);

        return true; // Email updated successfully
    }

    @Override
    @Transactional
    public boolean updatePassword(String email, String currentPassword, String newPassword) {
        // Find the user by email
        User user = userRepository.findByEmail(email);

        if (user == null) {
            // User with provided email does not exist
            return false;
        }

        // Check if the current password matches
        if (!user.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("Current password is incorrect.");
        }

        // Update the password
        user.setPassword(newPassword); // Make sure to hash/encrypt the password in production
        userRepository.save(user);

        return true; // Password updated successfully
    }
    
    @Override
    public CandidateDTO getCandidateById(String candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
            .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));
        
        // Convert Candidate entity to CandidateDTO
        return convertToDTO(candidate);
    }

    private CandidateDTO convertToDTO(Candidate candidate) {
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setCandidateId(candidate.getCandidateId());
        candidateDTO.setEmail(candidate.getUser().getEmail());  // Assuming user entity has email field
        candidateDTO.setPassword(candidate.getUser().getPassword()); // You may choose to exclude the password from the DTO for security reasons
        candidateDTO.setFullName(candidate.getFullName());
        candidateDTO.setMobileNumber(candidate.getMobileNumber());
        candidateDTO.setGender(candidate.getGender());
        candidateDTO.setDateOfBirth(candidate.getDateOfBirth());
        candidateDTO.setWorkStatus(candidate.getWorkStatus());
        candidateDTO.setCurrentLocation(candidate.getCurrentLocation());
        candidateDTO.setHighestQualification(candidate.getHighestQualification());
        candidateDTO.setCourse(candidate.getCourse());
        candidateDTO.setSpecialization(candidate.getSpecialization());
        candidateDTO.setUniversity(candidate.getUniversity());
        candidateDTO.setWorkExperienceYears(candidate.getWorkExperienceYears());
        candidateDTO.setAnnualSalary(candidate.getAnnualSalary());
        candidateDTO.setResumeUrl(candidate.getResumeUrl());
        candidateDTO.setProfilePictureUrl(candidate.getProfilePictureUrl());
        candidateDTO.setProfileSummary(candidate.getProfileSummary());
        
        // Mapping key skills
        candidateDTO.setSelectedSkills(candidate.getKeySkills().stream()
            .map(skill -> skill.getSkill().getSkillName()) 
            .collect(Collectors.toList()));

        return candidateDTO;
    }

}
