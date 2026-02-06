package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

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
        int total = 0;

        // Перебираем все списки товаров
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }

        return total;
    }

    /**
     * Метод подсчета количества специальных товаров
     * @return количество специальных товаров в корзине
     */
    public int countSpecialProducts() {
        int count = 0;

        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                if (product.isSpecial()) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Метод печати содержимого корзины
     */
    public void printContents() {
        if (productsMap.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        // TreeMap автоматически сортирует ключи по имени
        for (Map.Entry<String, List<Product>> entry : productsMap.entrySet()) {
            String productName = entry.getKey();
            List<Product> products = entry.getValue();

            // Выводим каждый товар с данным именем
            for (Product product : products) {
                System.out.println(product.toString());
            }
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + countSpecialProducts());
    }

    public boolean containsProduct(String productName) {
        return productsMap.containsKey(productName);
    }

    public int getUniqueProductNamesCount() {
        return productsMap.size();
    }

     public int getTotalProductCount() {
        int total = 0;

        for (List<Product> productList : productsMap.values()) {
            total += productList.size();
        }

        return total;
    }


    public void clear() {
        productsMap.clear();
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        for (List<Product> productList : productsMap.values()) {
            allProducts.addAll(productList);
        }

        return allProducts;
    }

    public List<String> getAllProductNames() {
        return new ArrayList<>(productsMap.keySet());
    }
}

