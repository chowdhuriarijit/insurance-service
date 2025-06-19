package com.example.insuranceservice.service;

import com.example.insuranceservice.model.VehicleInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VehicleClient {

    private final RestTemplate restTemplate;

    @Value("${vehicle.service.url}")
    private String vehicleServiceUrl;

    public VehicleClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setBaseUrl(String baseUrl) {
        this.vehicleServiceUrl = baseUrl;
    }

    public VehicleInfo getVehicleInfo(String registrationNumber) {
        if (vehicleServiceUrl == null || vehicleServiceUrl.isEmpty()) {
            throw new IllegalStateException("Base URL is not set. Please set the base URL before making API calls.");
        }
        String url = vehicleServiceUrl + "/vehicle/" + registrationNumber;
        return restTemplate.getForObject(url, VehicleInfo.class);
    }
}
