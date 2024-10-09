package com.crimsonlogic.onlinejobportal.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;

public interface CandidateService {
	void saveCandidate(CandidateDTO candidateDTO);

	boolean loginCandidate(String email, String password);
	
	String getCandidateName(String userEmail);
	
	String getProfilePicUrl(String userEmail);
	
	CandidateDTO getCandidateByEmail(String email);
	
	boolean updateEmail(String currentEmail, String newEmail);
	
	boolean updatePassword(String email, String currentPassword, String newPassword);

	String updateProfilePicture(String userEmail, MultipartFile picture) throws IOException;

	void deleteProfilePicture(String userEmail);

	CandidateDTO getCandidateById(String candidateId);
		
}
