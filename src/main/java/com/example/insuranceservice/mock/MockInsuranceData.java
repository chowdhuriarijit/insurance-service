package com.example.insuranceservice.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockInsuranceData {
    // Used linked hash map to maintain insertion order
    private static final Map<String, List<MockInsurance>> insuranceData = new HashMap<>();

    /* Static block to initialize default data */
    static {
        resetToDefaultData();
    }

    /**
     * Get insurance data for a specific personId.
     * @param personId
     * @return
     */
    public static List<MockInsurance> getInsurancesByPersonId(String personId) {
        return insuranceData.getOrDefault(personId, Collections.emptyList());
    }

    /**
     * Add or update insurance data for a specific personId.
     * @param personId
     * @param insurances 
     */
    public static void addInsuranceData(String personId, List<MockInsurance> insurances) {
        insuranceData.put(personId, insurances);
    }

    /**
     * Clear all insurance data.
     */
    public static void clearAllData() {
        insuranceData.clear();
    }

    /**
     * Reset the insurance data to its default state.
     */
    public static void resetToDefaultData() {
        clearAllData();
        insuranceData.put("123", Arrays.asList(
            new MockInsurance(InsuranceType.PET_INSURANCE.getDisplayName(), 250.0, "Active"),
            new MockInsurance(InsuranceType.PERSONAL_HEALTH_INSURANCE.getDisplayName(), 1200.0, "Active"),
            new MockInsurance(InsuranceType.CAR_INSURANCE.getDisplayName(), 900.0, "Active")
        ));

        insuranceData.put("456", Arrays.asList(
            new MockInsurance(InsuranceType.HOME_INSURANCE.getDisplayName(), 1500.0, "Active"),
            new MockInsurance(InsuranceType.TRAVEL_INSURANCE.getDisplayName(), 300.0, "Active")
        ));
    }
}