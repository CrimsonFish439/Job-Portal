package com.crimsonlogic.onlinejobportal.service;

import com.crimsonlogic.onlinejobportal.entity.Role;
import com.crimsonlogic.onlinejobportal.entity.User;

public interface UserService {
    void saveUser(User user);
    Role findRoleByName(String roleName);
}
