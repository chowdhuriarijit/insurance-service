package com.example.insuranceservice.unit;


import com.example.insuranceservice.mock.InsuranceType;
import com.example.insuranceservice.mock.MockInsurance;
import com.example.insuranceservice.mock.MockInsuranceData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InsuranceServiceIntegrationTest {

    @Test
    void testAddCustomInsuranceData() {
        // Arrange
        String personId = "789";
        List<MockInsurance> customInsurances = Arrays.asList(
            new MockInsurance(InsuranceType.TRAVEL_INSURANCE.getDisplayName(), 500.0, "Active")
        );
        MockInsuranceData.addInsuranceData(personId, customInsurances);

        // Act
        List<MockInsurance> result = MockInsuranceData.getInsurancesByPersonId(personId);

        // Assert
        assertEquals(1, result.size());
        assertEquals("Travel insurance", result.get(0).getName());
    }

    @Test
    void testClearAllData() {
        // Arrange
        assertFalse(MockInsuranceData.getInsurancesByPersonId("123").isEmpty());

        // Act
        MockInsuranceData.clearAllData();

        // Assert
        assertTrue(MockInsuranceData.getInsurancesByPersonId("123").isEmpty());
    }
}
