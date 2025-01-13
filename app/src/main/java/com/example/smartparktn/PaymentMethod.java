package com.example.smartparktn;

public class PaymentMethod {
    private String name;
    private int iconResource;

    public PaymentMethod(String name, int iconResource) {
        this.name = name;
        this.iconResource = iconResource;
    }

    public String getName() { return name; }
    public int getIconResource() { return iconResource; }
}

