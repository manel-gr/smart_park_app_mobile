package com.example.smartparktn;

public class ParkingItem {
    private String name;
    private String address;
    private String distance;
    private String priceAndSpaces;
    private int imageResource;

    public ParkingItem(String name, String address, String distance, String priceAndSpaces, int imageResource) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.priceAndSpaces = priceAndSpaces;
        this.imageResource = imageResource;
    }

    // Getters
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getDistance() { return distance; }
    public String getPriceAndSpaces() { return priceAndSpaces; }
    public int getImageResource() { return imageResource; }
}

