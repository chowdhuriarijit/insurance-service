package com.example.insuranceservice.service;

import com.example.insuranceservice.mock.InsuranceType;
import com.example.insuranceservice.mock.MockInsurance;
import com.example.insuranceservice.mock.MockInsuranceData;
import com.example.insuranceservice.model.VehicleInfo;
import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class InsuranceService {

    private final VehicleClient vehicleClient;
    private final LDClient ldClient;

    public InsuranceService(VehicleClient vehicleClient, LDClient ldClient) {
        this.vehicleClient = vehicleClient;
        this.ldClient = ldClient;
    }

    public Map<String, Object> getInsuranceDetails(String personId) {
        // Used linked hash map to maintain insertion order
        Map<String, Object> response = new LinkedHashMap<>();

        List<MockInsurance> insurances = MockInsuranceData.getInsurancesByPersonId(personId);

        if (insurances.isEmpty()) {
            response.put("message", "No insurance data found for personId: " + personId);
        } else {
            response.put("personId", personId);
            response.put("insurances", insurances);
            // Calculate total insurance cost
            double totalCost = insurances.stream()
            .mapToDouble(MockInsurance::getPremium)
            .sum();
            response.put("totalInsuranceCost", totalCost);


            // Check if the person has car insurance
            boolean hasCarInsurance = insurances.stream()
                .anyMatch(insurance -> InsuranceType.CAR_INSURANCE.getDisplayName().equalsIgnoreCase(insurance.getName()));

            /*
             * Just for demo purpose we are using a static vehicle number "TEST123" and here 
             * we are assumming that person with personId "123" has a vehicle with number "TEST123".
             * In a real application, you would typically fetch the vehicle number from a database or an external service.
             */
            if (hasCarInsurance) {
                VehicleInfo vehicle = vehicleClient.getVehicleInfo("TEST123");
                response.put("vehicle", vehicle);
            }

            /* 
             * Here showing the feature flag implementation for individual user level in launch darkly
             * dashboard the default value of the discountEnabled feature flag is set to false.
             * The feature flag is enabled for the user with personId "456" in the LaunchDarkly dashboard.
             * The user with person id "456" will receive the feature flag value as true and 
             * for that user will get discount on total insurance cost.
             */
            LDContext context = LDContext.builder(personId).build();
            boolean flagValue = ldClient.boolVariation("discountEnabled", context, false);

            if (flagValue) {
                // Here I just used hardcoded discount value of 10% for demo purpose.
                // In a real application, you might calculate the discount based on various factors.
                // Also it can configured in LaunchDarkly dashboard.
                // For example, you might have different discount rates for different types of insurance.
                double discount = totalCost * 0.1; // Apply a 10% discount
                totalCost = totalCost - discount;
                response.put("discountApplied", "true");
                response.put("discountAmount", discount);
                response.put("discountedTotalInsuranceCost", totalCost);
            } else {
                response.put("discountApplied", "false");
            }

        
        }

        return response;
    }
}