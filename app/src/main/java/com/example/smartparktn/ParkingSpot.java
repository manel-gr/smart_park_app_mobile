package com.example.smartparktn;

public class ParkingSpot {
    private int number;
    private boolean isAvailable;

    public ParkingSpot(int number, boolean isAvailable) {
        this.number = number;
        this.isAvailable = isAvailable;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

