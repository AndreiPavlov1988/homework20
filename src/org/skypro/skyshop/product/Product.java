package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;
import java.util.Objects;

public abstract class Product implements Searchable {
    private final String name;


    protected Product(String name) {
        validateName(name);
        this.name = name;
    }


    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Название товара не может быть null, пустой строкой или состоять только из пробелов"
            );
        }
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public String getSearchTerm() {
        return name;
    }


    @Override
    public String getContentType() {
        return "PRODUCT";
    }


    public abstract int getPrice();


    public abstract boolean isSpecial();


    @Override
    public abstract String toString();


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(name, product.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}