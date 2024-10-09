package com.crimsonlogic.onlinejobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.onlinejobportal.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByRoleName(String roleName);
	
}
