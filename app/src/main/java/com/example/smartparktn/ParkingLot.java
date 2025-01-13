package com.example.smartparktn;

public class ParkingLot {
    private String name;
    private String distance;
    private String availability;
    private float pricePerHour;

    public ParkingLot(String name, String distance, String availability, float pricePerHour) {
        this.name = name;
        this.distance = distance;
        this.availability = availability;
        this.pricePerHour = pricePerHour;
    }

    public String getName() { return name; }
    public String getDistance() { return distance; }
    public String getAvailability() { return availability; }
    public float getPricePerHour() { return pricePerHour; }
}

