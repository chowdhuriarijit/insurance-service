package com.example.insuranceservice.integration;

import com.example.insuranceservice.mock.MockInsurance;
import com.example.insuranceservice.mock.MockInsuranceData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InsuranceServiceApiIntegrationTest {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${insurance.service.host}") // Default to localhost:8080 if not set
    private String host;


    @Test
    void testGetInsuranceDetailsApi() {
        // Arrange
        String personId = "456";
        MockInsuranceData.addInsuranceData(personId, List.of(
            new MockInsurance("Health Insurance", 1000.0, "Active"),
            new MockInsurance("Car Insurance", 500.0, "Active")
        ));

        // Use the host from properties
        String url = host + "/api/v1/insurance/" + personId;

        // Act
        ResponseEntity<Map<String, Object>> response = restTemplate.getForEntity(url, (Class<Map<String, Object>>) (Class<?>) Map.class);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        Map<String, Object> responseBody = response.getBody();
        assertEquals(personId, responseBody.get("personId"));
        assertEquals(1800.0, responseBody.get("totalInsuranceCost"));
    }
}
