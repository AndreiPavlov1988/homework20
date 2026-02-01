package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private static final int BASKET_CAPACITY = 5;
    private final Product[] products;
    private int productCount;

    /**
     * Конструктор корзины
     */
    public ProductBasket() {
        this.products = new Product[BASKET_CAPACITY];
        this.productCount = 0;
    }

    /**
     * Метод добавления продукта в корзину
     * @param product добавляемый продукт
     */
    public void addProduct(Product product) {
        if (productCount >= BASKET_CAPACITY) {
            System.out.println("Невозможно добавить продукт");
            return;
        }
        products[productCount] = product;
        productCount++;
    }

    /**
     * Метод получения общей стоимости корзины
     * @return общая стоимость всех товаров в корзине
     */
    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    /**
     * Метод подсчета количества специальных товаров
     * @return количество специальных товаров в корзине
     */
    public int countSpecialProducts() {
        int count = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i].isSpecial()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Метод печати содержимого корзины
     */
    public void printContents() {
        if (productCount == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        for (int i = 0; i < productCount; i++) {
            Product product = products[i];
            System.out.println(product.toString());
        }
        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + countSpecialProducts());
    }

    /**
     * Метод проверки наличия продукта в корзине по имени
     * @param productName имя продукта для поиска
     * @return true если продукт найден, false если нет
     */
    public boolean containsProduct(String productName) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод очистки корзины
     */
    public void clear() {
        for (int i = 0; i < BASKET_CAPACITY; i++) {
            products[i] = null;
        }
        productCount = 0;
    }
}

