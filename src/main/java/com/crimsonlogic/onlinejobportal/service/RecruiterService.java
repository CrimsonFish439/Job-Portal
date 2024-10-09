package com.crimsonlogic.onlinejobportal.service;

import com.crimsonlogic.onlinejobportal.dto.RecruiterDTO;

public interface RecruiterService {

	void saveRecruiter(RecruiterDTO recruiterDTO);

	boolean loginRecruiter(String email, String password);

	RecruiterDTO getRecruiterByEmail(String email);

}
