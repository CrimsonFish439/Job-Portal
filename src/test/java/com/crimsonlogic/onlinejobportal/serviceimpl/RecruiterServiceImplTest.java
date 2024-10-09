package com.crimsonlogic.onlinejobportal.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crimsonlogic.onlinejobportal.dto.RecruiterDTO;
import com.crimsonlogic.onlinejobportal.entity.Industry;
import com.crimsonlogic.onlinejobportal.entity.Recruiter;
import com.crimsonlogic.onlinejobportal.entity.Role;
import com.crimsonlogic.onlinejobportal.entity.User;
import com.crimsonlogic.onlinejobportal.repository.IndustryRepository;
import com.crimsonlogic.onlinejobportal.repository.RecruiterRepository;
import com.crimsonlogic.onlinejobportal.repository.RoleRepository;
import com.crimsonlogic.onlinejobportal.repository.UserRepository;

class RecruiterServiceImplTest {

    @Mock
    private RecruiterRepository recruiterRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IndustryRepository industryRepository;

    @InjectMocks
    private RecruiterServiceImpl recruiterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRecruiter() {
        // Arrange
        RecruiterDTO recruiterDTO = new RecruiterDTO();
        recruiterDTO.setOfficialEmail("recruiter@example.com");
        recruiterDTO.setPassword("password");
        recruiterDTO.setFullName("John Doe");
        recruiterDTO.setIndustry("IT");
        
        Role recruiterRole = new Role();
        when(roleRepository.findByRoleName("RECRUITER")).thenReturn(recruiterRole);

        Industry industry = new Industry();
        when(industryRepository.findByIndustryName("IT")).thenReturn(Optional.of(industry));

        // Act
        recruiterService.saveRecruiter(recruiterDTO);

        // Assert
        verify(userRepository).save(any(User.class));
        verify(recruiterRepository).save(any(Recruiter.class));
    }

    @Test
    void testSaveRecruiter_IndustryNotFound() {
        // Arrange
        RecruiterDTO recruiterDTO = new RecruiterDTO();
        recruiterDTO.setIndustry("NonExistentIndustry");

        when(industryRepository.findByIndustryName("NonExistentIndustry"))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> recruiterService.saveRecruiter(recruiterDTO));
    }

    @Test
    void testLoginRecruiter_Success() {
        // Arrange
        User user = new User();
        user.setEmail("recruiter@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail("recruiter@example.com")).thenReturn(user);

        // Act
        boolean result = recruiterService.loginRecruiter("recruiter@example.com", "password");

        // Assert
        assertTrue(result);
    }

    @Test
    void testLoginRecruiter_Failure() {
        // Arrange
        when(userRepository.findByEmail("recruiter@example.com")).thenReturn(null);

        // Act
        boolean result = recruiterService.loginRecruiter("recruiter@example.com", "wrongpassword");

        // Assert
        assertTrue(!result);
    }

    @Test
    void testGetRecruiterByEmail() {
        // Arrange
        Recruiter recruiter = new Recruiter();
        recruiter.setFullName("John Doe");
        recruiter.setCompanyName("Tech Corp");
        recruiter.setIndustry(new Industry());
        recruiter.getIndustry().setIndustryName("IT");

        when(recruiterRepository.findByUserEmail("recruiter@example.com")).thenReturn(recruiter);

        // Act
        RecruiterDTO result = recruiterService.getRecruiterByEmail("recruiter@example.com");

        // Assert
        assertEquals("John Doe", result.getFullName());
        assertEquals("Tech Corp", result.getCompanyName());
        assertEquals("IT", result.getIndustry());
    }

    @Test
    void testGetRecruiterByEmail_NotFound() {
        // Arrange
        when(recruiterRepository.findByUserEmail("recruiter@example.com")).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> recruiterService.getRecruiterByEmail("recruiter@example.com"));
    }
}
