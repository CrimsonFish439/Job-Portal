package com.crimsonlogic.onlinejobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.onlinejobportal.entity.Industry;
import com.crimsonlogic.onlinejobportal.service.IndustryService;

@RestController
@RequestMapping("/api/industries")
public class IndustryController {

    @Autowired
    private IndustryService industryService;

    @GetMapping("/getallindustries")
    public List<Industry> getAllIndustries() {
        return industryService.getAllIndustries();
    }
}

