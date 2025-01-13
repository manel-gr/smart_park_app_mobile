package com.example.smartparktn;

public class SubscriptionOption {
    private String name;
    private String price;
    private int months;

    public SubscriptionOption(String name, String price, int months) {
        this.name = name;
        this.price = price;
        this.months = months;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getMonths() { return months; }
}

