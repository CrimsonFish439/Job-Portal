package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.dto.SkillDTO;
import com.crimsonlogic.onlinejobportal.repository.SkillRepository;
import com.crimsonlogic.onlinejobportal.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                              .map(skill -> new SkillDTO(skill.getSkillId(), skill.getSkillName()))
                              .collect(Collectors.toList());
    }
}
