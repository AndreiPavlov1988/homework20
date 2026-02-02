package org.skypro.skyshop.product;

public abstract class Product {
    private final String name;


    protected Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract int getPrice();

    public abstract boolean isSpecial();

    public abstract String toString();
}
