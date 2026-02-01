package org.skypro.skyshop.product;

/**
 * Класс обычного товара
 */
public class SimpleProduct extends Product {
    private final int price;


    public SimpleProduct(String name, int price) {
        super(name);
        this.price = price;
    }

    /**
     * Получить цену товара
     * @return цена товара
     */
    @Override
    public int getPrice() {
        return price;
    }

    /**
     * Проверяет, является ли товар специальным
     * @return false - обычный товар не является специальным
     */
    @Override
    public boolean isSpecial() {
        return false;
    }

    /**
     * Строковое представление товара
     * @return строка формата "название: цена"
     */
    @Override
    public String toString() {
        return getName() + ": " + getPrice();
    }
}