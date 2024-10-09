package com.crimsonlogic.onlinejobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.SkillDTO;

@Service
public interface SkillService {
	List<SkillDTO> getAllSkills();
}
