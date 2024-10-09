package com.crimsonlogic.onlinejobportal.entity;

import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    private String roleId = IDGenerator.generateID("ROLE");

    @Column(name = "role_name", unique = true)
    private String roleName;  //ADMIN, RECRUITER, CANDIDATE

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

    
    
}
