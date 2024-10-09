package com.crimsonlogic.onlinejobportal.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crimsonlogic.onlinejobportal.entity.Role;
import com.crimsonlogic.onlinejobportal.entity.User;
import com.crimsonlogic.onlinejobportal.repository.RoleRepository;
import com.crimsonlogic.onlinejobportal.repository.UserRepository;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");

        // Act
        userService.saveUser(user);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testFindRoleByName() {
        // Arrange
        Role role = new Role();
        role.setRoleName("ADMIN");

        when(roleRepository.findByRoleName("ADMIN")).thenReturn(role);

        // Act
        Role result = userService.findRoleByName("ADMIN");

        // Assert
        assertEquals("ADMIN", result.getRoleName());
        verify(roleRepository, times(1)).findByRoleName("ADMIN");
    }
}
