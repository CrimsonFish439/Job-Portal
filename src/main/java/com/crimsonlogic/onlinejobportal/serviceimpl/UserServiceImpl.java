package com.crimsonlogic.onlinejobportal.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.entity.Role;
import com.crimsonlogic.onlinejobportal.entity.User;
import com.crimsonlogic.onlinejobportal.repository.RoleRepository;
import com.crimsonlogic.onlinejobportal.repository.UserRepository;
import com.crimsonlogic.onlinejobportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}

