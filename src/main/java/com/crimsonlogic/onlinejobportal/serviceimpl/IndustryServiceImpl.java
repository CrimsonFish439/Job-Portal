package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crimsonlogic.onlinejobportal.entity.Industry;
import com.crimsonlogic.onlinejobportal.repository.IndustryRepository;
import com.crimsonlogic.onlinejobportal.service.IndustryService;

@Service
public class IndustryServiceImpl implements IndustryService {
    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public List<Industry> getAllIndustries() {
        return industryRepository.findAll();
    }
}
