package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercent;

    /**
     * Конструктор товара со скидкой
     * @param name название товара
     * @param basePrice базовая цена товара
     * @param discountPercent скидка в процентах (0-100)
     */
    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name);
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    /**
     * Получить цену товара со скидкой
     * @return цена товара со скидкой
     */
    @Override
    public int getPrice() {
        return basePrice * (100 - discountPercent) / 100;
    }

    /**
     * Получить базовую цену товара
     * @return базовая цена
     */
    public int getBasePrice() {
        return basePrice;
    }

    /**
     * Получить размер скидки в процентах
     * @return размер скидки
     */
    public int getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Проверяет, является ли товар специальным
     * @return true - товар со скидкой является специальным
     */
    @Override
    public boolean isSpecial() {
        return true;
    }

    /**
     * Строковое представление товара
     * @return строка формата "название: цена со скидкой (скидка%)"
     */
    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discountPercent + "%)";
    }
}
