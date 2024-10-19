package org.example.womenshopfx;

import javafx.beans.property.*;

public class Product {
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty purchasePrice;
    private final DoubleProperty sellPrice;
    private final DoubleProperty discount;
    private final StringProperty size;

    public Product(int id, String name, double purchasePrice, double sellPrice, double discount, String size) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
        this.sellPrice = new SimpleDoubleProperty(sellPrice);
        this.discount = new SimpleDoubleProperty(discount);
        this.size = new SimpleStringProperty(size);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public DoubleProperty purchasePriceProperty() {
        return purchasePrice;
    }

    public DoubleProperty sellPriceProperty() {
        return sellPrice;
    }

    public DoubleProperty discountProperty() {
        return discount;
    }

    public StringProperty sizeProperty() {
        return size;
    }
}
