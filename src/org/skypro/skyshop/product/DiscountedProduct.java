package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountPercent;

    /**
     * Конструктор товара со скидкой
     * @param name название товара
     * @param basePrice базовая цена товара
     * @param discountPercent скидка в процентах (0-100)
     * @throws IllegalArgumentException если цены или скидка невалидны
     */
    public DiscountedProduct(String name, int basePrice, int discountPercent) {
        super(name);
        validateBasePrice(basePrice);
        validateDiscountPercent(discountPercent);
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    /**
     * Проверяет валидность базовой цены
     * @param basePrice базовая цена для проверки
     * @throws IllegalArgumentException если цена невалидная
     */
    private void validateBasePrice(int basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException(
                    "Базовая цена товара должна быть строго больше 0. Получено: " + basePrice
            );
        }
    }

    /**
     * Проверяет валидность процента скидки
     * @param discountPercent процент скидки для проверки
     * @throws IllegalArgumentException если процент скидки невалидный
     */
    private void validateDiscountPercent(int discountPercent) {
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException(
                    "Процент скидки должен быть в диапазоне от 0 до 100 включительно. Получено: " + discountPercent
            );
        }
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
