package com.crimsonlogic.onlinejobportal.dto;

import com.crimsonlogic.onlinejobportal.entity.User;
import com.crimsonlogic.onlinejobportal.util.IDGenerator;

public class AdminDTO {
    private String adminId = IDGenerator.generateID("ADM");
    private User user;
    private String adminName;
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
}
