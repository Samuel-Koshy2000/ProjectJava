package org.example.womenshopfx;

public class Product {
    private int id;
    private String name;
    private double purchasePrice;
    private double sellPrice;
    private double discount;
    private String size;

    // Constructor
    public Product(int id, String name, double purchasePrice, double sellPrice, double discount, String size) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.discount = discount;
        this.size = size;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public String getSize() {
        return size;
    }
}