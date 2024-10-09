package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.QualificationDTO;
import com.crimsonlogic.onlinejobportal.entity.Qualification;
import com.crimsonlogic.onlinejobportal.repository.QualificationRepository;
import com.crimsonlogic.onlinejobportal.service.QualificationService;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public List<QualificationDTO> getAllQualifications() {
        // Fetch the list of Qualification entities from the repository
        List<Qualification> qualifications = qualificationRepository.findAll();

        // Convert the list of Qualification entities to QualificationDTOs
        return qualifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert Qualification entity to DTO
    private QualificationDTO convertToDTO(Qualification qualification) {
        QualificationDTO dto = new QualificationDTO();
        dto.setQualificationId(qualification.getQualificationId());
        dto.setQualificationName(qualification.getQualificationName()); // Assuming you have a name field
        // Add more fields as needed
        return dto;
    }
}

