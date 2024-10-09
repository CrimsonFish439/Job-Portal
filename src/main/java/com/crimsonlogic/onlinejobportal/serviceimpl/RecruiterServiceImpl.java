package com.crimsonlogic.onlinejobportal.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimsonlogic.onlinejobportal.dto.RecruiterDTO;
import com.crimsonlogic.onlinejobportal.entity.Industry;
import com.crimsonlogic.onlinejobportal.entity.Recruiter;
import com.crimsonlogic.onlinejobportal.entity.Role;
import com.crimsonlogic.onlinejobportal.entity.User;
import com.crimsonlogic.onlinejobportal.repository.IndustryRepository;
import com.crimsonlogic.onlinejobportal.repository.RecruiterRepository;
import com.crimsonlogic.onlinejobportal.repository.RoleRepository;
import com.crimsonlogic.onlinejobportal.repository.UserRepository;
import com.crimsonlogic.onlinejobportal.service.RecruiterService;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Autowired
    private RecruiterRepository recruiterRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private IndustryRepository industryRepository;

    private static final String UPLOAD_DIRECTORY = "/uploads/company-logos/";

    @Transactional
    public void saveRecruiter(RecruiterDTO recruiterDTO) {
        // Create a new User entity
        User user = new User();
        user.setEmail(recruiterDTO.getOfficialEmail());
        user.setPassword(recruiterDTO.getPassword());  // Encrypt the password if needed
        user.setStatus(true);  // Account is active by default

        // Assign the role for Recruiter
        Role recruiterRole = roleRepository.findByRoleName("RECRUITER");
        if (recruiterRole == null) {
            throw new RuntimeException("Recruiter role not found!");
        }
        user.setRole(recruiterRole);

        // Save the User entity
        userRepository.save(user);
        System.out.println("User saved: " + user.getEmail() + " with role: " + recruiterRole.getRoleName());

        // Create a new Recruiter entity
        Recruiter recruiter = new Recruiter();
        recruiter.setUser(user);
        recruiter.setFullName(recruiterDTO.getFullName());
        recruiter.setOfficialEmail(recruiterDTO.getOfficialEmail());
        recruiter.setCompanyName(recruiterDTO.getCompanyName());
        recruiter.setDesignation(recruiterDTO.getDesignation());
        recruiter.setCompanyLocation(recruiterDTO.getCompanyLocation());
        recruiter.setCompanyAddress(recruiterDTO.getCompanyAddress());
        recruiter.setAboutCompany(recruiterDTO.getAboutCompany());
        recruiter.setEmployeeRange(recruiterDTO.getEmployeeRange());

        // Set the industry by looking it up from the Industry repository
        Industry industry = industryRepository.findByIndustryName(recruiterDTO.getIndustry())
                .orElseThrow(() -> new RuntimeException("Industry not found!"));
        recruiter.setIndustry(industry);

        // Set the company logo URL if provided
        recruiter.setCompanyLogoUrl(recruiterDTO.getCompanyLogoUrl());

        // Save the Recruiter entity
        recruiterRepository.save(recruiter);
        System.out.println("Recruiter saved with fullName: " + recruiter.getFullName());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean loginRecruiter(String email, String password) {
        User user = userRepository.findByEmail(email);

        // Validate that user exists and password matches
        if (user != null && user.getPassword().equals(password)) {
            return true;  // Login success
        }
        return false;  // Login failure
    }
    
    @Transactional
    @Override
    public RecruiterDTO getRecruiterByEmail(String email) {
        // Fetch recruiter by the email in User entity
        Recruiter recruiter = recruiterRepository.findByUserEmail(email);
        
        if (recruiter == null) {
            throw new RuntimeException("Recruiter not found with email: " + email);
        }
        
        // Map Recruiter entity to RecruiterDTO
        RecruiterDTO recruiterDTO = new RecruiterDTO();
        recruiterDTO.setFullName(recruiter.getFullName());
        recruiterDTO.setCompanyName(recruiter.getCompanyName());
        recruiterDTO.setEmployeeRange(recruiter.getEmployeeRange());
        recruiterDTO.setDesignation(recruiter.getDesignation());
        recruiterDTO.setCompanyLocation(recruiter.getCompanyLocation());
        recruiterDTO.setCompanyAddress(recruiter.getCompanyAddress());
        recruiterDTO.setAboutCompany(recruiter.getAboutCompany());
        recruiterDTO.setIndustry(recruiter.getIndustry().getIndustryName());
        recruiterDTO.setCompanyLogoUrl(recruiter.getCompanyLogoUrl());
        
        return recruiterDTO;
    }


	
}

