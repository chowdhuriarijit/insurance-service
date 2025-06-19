package com.example.insuranceservice.mock;

public enum InsuranceType {
    PET_INSURANCE("Pet insurance"),
    PERSONAL_HEALTH_INSURANCE("Personal health insurance"),
    CAR_INSURANCE("Car insurance"),
    HOME_INSURANCE("Home insurance"),
    TRAVEL_INSURANCE("Travel insurance"),
    LIABILITY_INSURANCE("Liability insurance");

    

    private final String displayName;

    InsuranceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}