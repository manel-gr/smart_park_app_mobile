package com.example.smartparktn.models;

public class Car {
    private String id;
    private String name;
    private String licensePlate;
    private String country;
    private boolean hasGreenLicense;
    private String userId;

    public Car() {
        // Required empty constructor for Firebase
    }

    public Car(String name, String licensePlate, String country, boolean hasGreenLicense, String userId) {
        this.name = name;
        this.licensePlate = licensePlate;
        this.country = country;
        this.hasGreenLicense = hasGreenLicense;
        this.userId = userId;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public boolean isHasGreenLicense() { return hasGreenLicense; }
    public void setHasGreenLicense(boolean hasGreenLicense) { this.hasGreenLicense = hasGreenLicense; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}

