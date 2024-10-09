package com.crimsonlogic.onlinejobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crimsonlogic.onlinejobportal.dto.SpecializationDTO;
import com.crimsonlogic.onlinejobportal.service.SpecializationService;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @GetMapping("/bycourse/{course}")
    public ResponseEntity<List<SpecializationDTO>> getSpecializationsByCourse(@PathVariable String course) {
        List<SpecializationDTO> specializations = specializationService.getSpecializationsByCourse(course);
        return ResponseEntity.ok(specializations);
    }
}

