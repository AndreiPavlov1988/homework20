package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;
import java.util.stream.Collectors;


public class ProductBasket {
    private final Map<String, List<Product>> productsMap;


    public ProductBasket() {
        // Используем TreeMap для автоматической сортировки по имени
        this.productsMap = new TreeMap<>();
    }


    public void addProduct(Product product) {
        String name = product.getName();

        // Если товара с таким именем еще нет, создаем новый список
        productsMap.putIfAbsent(name, new ArrayList<>());

        // Добавляем товар в список по имени
        productsMap.get(name).add(product);
    }

    public List<Product> removeProductsByName(String name) {
        // Удаляем весь список товаров с данным именем
        List<Product> removedProducts = productsMap.remove(name);

        // Если товаров не было, возвращаем пустой список
        return removedProducts != null ? removedProducts : new ArrayList<>();
    }


    public List<Product> getProductsByName(String name) {
        return productsMap.getOrDefault(name, new ArrayList<>());
    }


    public int getTotalPrice() {
        return getAllProductsStream()
                .mapToInt(Product::getPrice)
                .sum();
    }


    private long getSpecialCount() {
        return getAllProductsStream()
                .filter(Product::isSpecial)
                .count();
    }


    public void printContents() {
        if (productsMap.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        // Выводим все товары
        getAllProductsStream()
                .forEach(System.out::println);

        // Выводим итоговую информацию
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }


    private java.util.stream.Stream<Product> getAllProductsStream() {
        return productsMap.values().stream()
                .flatMap(Collection::stream);
    }


    public boolean containsProduct(String productName) {
        return productsMap.containsKey(productName);
    }


    public int getUniqueProductNamesCount() {
        return productsMap.size();
    }


    public int getTotalProductCount() {
        return (int) getAllProductsStream().count();
    }


    public void clear() {
        productsMap.clear();
    }


    public List<Product> getAllProducts() {
        return getAllProductsStream()
                .collect(Collectors.toList());
    }


    public List<String> getAllProductNames() {
        return new ArrayList<>(productsMap.keySet());
    }
}

