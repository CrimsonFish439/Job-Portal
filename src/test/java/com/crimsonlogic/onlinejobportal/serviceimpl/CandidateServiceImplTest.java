package com.crimsonlogic.onlinejobportal.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.entity.Candidate;
import com.crimsonlogic.onlinejobportal.entity.Role;
import com.crimsonlogic.onlinejobportal.entity.Skill;
import com.crimsonlogic.onlinejobportal.entity.User;
import com.crimsonlogic.onlinejobportal.exception.DuplicateEmailException;
import com.crimsonlogic.onlinejobportal.repository.CandidateRepository;
import com.crimsonlogic.onlinejobportal.repository.RoleRepository;
import com.crimsonlogic.onlinejobportal.repository.SkillRepository;
import com.crimsonlogic.onlinejobportal.repository.UserRepository;

class CandidateServiceImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private SkillRepository skillRepository; // Added mock for SkillRepository

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCandidate_DuplicateEmailException() {
        // Arrange
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setEmail("existing.email@example.com");

        when(userRepository.findByEmail(candidateDTO.getEmail())).thenReturn(new User());

        // Act & Assert
        assertThrows(DuplicateEmailException.class, () -> candidateService.saveCandidate(candidateDTO));
    }

    @Test
    void testSaveCandidate_Success() {
        // Arrange
        CandidateDTO candidateDTO = new CandidateDTO();
        candidateDTO.setFullName("John Doe");
        candidateDTO.setEmail("john@example.com");
        candidateDTO.setPassword("password");
        candidateDTO.setMobileNumber("1234567890");
        candidateDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
        candidateDTO.setGender("Male");
        candidateDTO.setCurrentLocation("New York");
        candidateDTO.setHighestQualification("Bachelor's");
        candidateDTO.setWorkExperienceYears(5);
        candidateDTO.setAnnualSalary(new BigDecimal("50000"));
        candidateDTO.setSelectedSkills(Arrays.asList("skill1", "skill2")); // Set selected skills

        when(userRepository.findByEmail(candidateDTO.getEmail())).thenReturn(null);
        when(roleRepository.findByRoleName("CANDIDATE")).thenReturn(new Role());

        // Create mock Skill objects and set their IDs and names using setters
        Skill skill1 = new Skill();
        skill1.setSkillId("skill1");
        skill1.setSkillName("Skill 1");

        Skill skill2 = new Skill();
        skill2.setSkillId("skill2");
        skill2.setSkillName("Skill 2");

        // Mock the behavior of skillRepository.findById()
        when(skillRepository.findById("skill1")).thenReturn(Optional.of(skill1));
        when(skillRepository.findById("skill2")).thenReturn(Optional.of(skill2));

        // Act
        candidateService.saveCandidate(candidateDTO);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
        verify(candidateRepository, times(1)).save(any(Candidate.class));
    }


    @Test
    void testUpdateProfilePicture() throws Exception {
        // Arrange
        User user = new User();
        Candidate candidate = new Candidate();
        MultipartFile picture = mock(MultipartFile.class);

        when(userRepository.findByEmail("john@example.com")).thenReturn(user);
        when(candidateRepository.findByUser(user)).thenReturn(candidate);
        when(picture.getOriginalFilename()).thenReturn("profile.jpg");

        // Act
        String profilePicUrl = candidateService.updateProfilePicture("john@example.com", picture);

        // Assert
        assertEquals("profile_pictures/profile.jpg", profilePicUrl);
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    void testDeleteProfilePicture() {
        // Arrange
        User user = new User();
        Candidate candidate = new Candidate();
        candidate.setProfilePictureUrl("profile_pictures/profile.jpg");

        when(userRepository.findByEmail("john@example.com")).thenReturn(user);
        when(candidateRepository.findByUser(user)).thenReturn(candidate);

        // Act
        candidateService.deleteProfilePicture("john@example.com");

        // Assert
        assertNull(candidate.getProfilePictureUrl());
        verify(candidateRepository, times(1)).save(candidate);
    }
}
