package com.crimsonlogic.onlinejobportal.entity;

import com.crimsonlogic.onlinejobportal.util.IDGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * The User entity represents the authentication and basic account information of a user in the system.
 * Each user can be assigned a specific role, such as Admin, Recruiter, or Candidate.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * The unique identifier for each user.
     * It is auto-generated with the prefix "USR" to distinguish it from other entity IDs.
     */
    @Id
    @Column(name = "user_id")
    private String userId = IDGenerator.generateID("USR");

    /**
     * The email address associated with the user.
     * This field is unique and is used for authentication purpose.
     */
    @Column(name = "user_email", unique = true)
    private String email;

    /**
     * The encrypted password for the user's account.
     * This is used for authentication and stored securely.
     */
    @Column(name = "user_password")
    private String password;

    /**
     * Indicates whether the user account is active.
     * A value of true means the account is active, while false means the account is disabled or inactive.
     */
    @Column(name = "user_status")
    private Boolean status = true;

    /**
     * The role assigned to the user.
     * This field establishes a many-to-one relationship between the User and the Role entity.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
    
    
}
