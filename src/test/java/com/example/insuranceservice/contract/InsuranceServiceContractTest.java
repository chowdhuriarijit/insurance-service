package com.example.insuranceservice.contract;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.example.insuranceservice.model.VehicleInfo;
import com.example.insuranceservice.service.VehicleClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

// Define the provider and mock server port
@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "VehicleService", port = "8081")
public class InsuranceServiceContractTest {

    @Autowired
    private VehicleClient vehicleClient; 
    @Pact(consumer = "InsuranceService")
    public V4Pact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
            .given("A vehicle with registration number TEST123 exists")
            .uponReceiving("A request for vehicle details")
            .path("/api/v1/vehicle/TEST123")
            .method("GET")
            .willRespondWith()
            .status(200)
            .headers(headers)
            .body("{\"registrationNumber\":\"TEST123\",\"make\":\"Toyota\",\"model\":\"Corolla\"}")
            .toPact(V4Pact.class);
    }

    @Test
    void testFetchVehicleDataForPersonId() {
        // Act
        VehicleInfo vehicleInfo = vehicleClient.getVehicleInfo("TEST123");

        // Assert
        assertEquals("TEST123", vehicleInfo.getRegistrationNumber());
        assertEquals("Toyota", vehicleInfo.getMake());
        assertEquals("Corolla", vehicleInfo.getModel());
    
    }
}