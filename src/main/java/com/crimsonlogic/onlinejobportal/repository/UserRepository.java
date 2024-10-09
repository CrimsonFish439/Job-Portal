package com.crimsonlogic.onlinejobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crimsonlogic.onlinejobportal.entity.User;


public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
}
