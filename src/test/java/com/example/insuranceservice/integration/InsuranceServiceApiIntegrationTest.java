package com.example.insuranceservice.integration;

import com.example.insuranceservice.mock.MockInsurance;
import com.example.insuranceservice.mock.MockInsuranceData;
import com.example.insuranceservice.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class InsuranceServiceApiIntegrationTest {

    @Autowired
    private InsuranceService insuranceService; // Directly use the service instead of RestTemplate

    @Test
    void testGetInsuranceDetailsApi() {
        // Arrange
        String personId = "456";
        MockInsuranceData.addInsuranceData(personId, List.of(
            new MockInsurance("Health Insurance", 1000.0, "Active"),
            new MockInsurance("Car Insurance", 500.0, "Active")
        ));

        // Act
        Map<String, Object> response = insuranceService.getInsuranceDetails(personId);

        // Assert
        assertEquals(personId, response.get("personId"));
        assertEquals(1500.0, response.get("totalInsuranceCost"));
             // Cast the 'insurances' field to List<MockInsurance>
        List<MockInsurance> insurances = (List<MockInsurance>) response.get("insurances");
        assertEquals(2, insurances.size());

        // Validate the first insurance item
        assertEquals("Health Insurance", insurances.get(0).getName());
        assertEquals(1000.0, insurances.get(0).getPremium());
        assertEquals("Active", insurances.get(0).getStatus());

        // Validate the second insurance item
        assertEquals("Car Insurance", insurances.get(1).getName());
        assertEquals(500.0, insurances.get(1).getPremium());
        assertEquals("Active", insurances.get(1).getStatus());
    }
}
