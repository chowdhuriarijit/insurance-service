package com.example.insuranceservice.controller;

import com.example.insuranceservice.service.InsuranceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/insurance")
public class InsuranceControllerV1 {

    private final InsuranceService insuranceService;

    public InsuranceControllerV1(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping("/{personId}")
    public ResponseEntity<?> getInsurance(@PathVariable String personId) {
        Map<String, Object> response = insuranceService.getInsuranceDetails(personId);
        return ResponseEntity.ok(response);
    }
}
