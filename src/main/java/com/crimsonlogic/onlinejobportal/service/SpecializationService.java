package com.crimsonlogic.onlinejobportal.service;

import java.util.List;

import com.crimsonlogic.onlinejobportal.dto.SpecializationDTO;

public interface SpecializationService {
    List<SpecializationDTO> getSpecializationsByCourse(String courseName);
}
