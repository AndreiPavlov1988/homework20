package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price) {
        super(name);
        validatePrice(price);
        this.price = price;
    }

    private void validatePrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Цена товара должна быть строго больше 0. Получено: " + price
            );
        }
    }


    @Override
    public int getPrice() {
        return price;
    }


    @Override
    public boolean isSpecial() {
        return false;
    }


    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }
}