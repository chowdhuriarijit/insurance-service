package com.example.insuranceservice.mock;

public class MockInsurance {
    private String name;
    private double premium; 
    private String status;

    public MockInsurance(String name, double premium, String status) {
        this.name = name;
        this.premium = premium;
        this.status = status;
    }

    public double getPremium() {
        return premium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}