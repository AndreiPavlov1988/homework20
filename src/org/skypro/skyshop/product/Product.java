package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;


    protected Product(String name) {
        this.name = name;
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
}
