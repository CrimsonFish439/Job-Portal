package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.SpecializationDTO;
import com.crimsonlogic.onlinejobportal.entity.Specialization;
import com.crimsonlogic.onlinejobportal.repository.SpecializationRepository;
import com.crimsonlogic.onlinejobportal.service.SpecializationService;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Override
    public List<SpecializationDTO> getSpecializationsByCourse(String courseName) {
        List<Specialization> specializations = specializationRepository.findByCourse_CourseName(courseName);
        return specializations.stream()
                .map(specialization -> {
                    SpecializationDTO dto = new SpecializationDTO();
                    dto.setSpecializationId(specialization.getSpecializationId());
                    dto.setSpecializationName(specialization.getSpecializationName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}

