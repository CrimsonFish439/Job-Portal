package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.UniversityDTO;
import com.crimsonlogic.onlinejobportal.entity.University;
import com.crimsonlogic.onlinejobportal.repository.UniversityRepository;
import com.crimsonlogic.onlinejobportal.service.UniversityService;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;

    @Override
    public List<UniversityDTO> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        return universities.stream()
                .map(university -> {
                    UniversityDTO dto = new UniversityDTO();
                    dto.setUniversityId(university.getUniversityId());
                    dto.setUniversityName(university.getUniversityName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

