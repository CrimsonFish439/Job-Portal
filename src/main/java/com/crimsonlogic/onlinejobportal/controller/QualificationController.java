package com.crimsonlogic.onlinejobportal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crimsonlogic.onlinejobportal.dto.QualificationDTO;
import com.crimsonlogic.onlinejobportal.service.QualificationService;

@RestController
@RequestMapping("/api/qualifications")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @GetMapping("/getallqualifications")
    public ResponseEntity<List<QualificationDTO>> getAllQualifications() {
        List<QualificationDTO> qualifications = qualificationService.getAllQualifications();
        return ResponseEntity.ok(qualifications);
    }
}
