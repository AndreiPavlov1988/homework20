package org.skypro.skyshop.product;

public class FixPriceProduct extends Product {
    // Константа фиксированной цены в стиле SCREAMING_SNAKE_CASE
    private static final int FIXED_PRICE = 200;

    /**
     * Конструктор товара с фиксированной ценой
     * @param name название товара
     */
    public FixPriceProduct(String name) {
        super(name);
    }

    /**
     * Получить фиксированную цену товара
     * @return фиксированная цена
     */
    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    /**
     * Получить значение фиксированной цены как константы
     * @return значение фиксированной цены
     */
    public static int getFixedPrice() {
        return FIXED_PRICE;
    }


    @Override
    public boolean isSpecial() {
        return true;
    }

    /**
     * Строковое представление товара
     * @return строка формата "название: Фиксированная цена значение"
     */
    @Override
    public String toString() {
        return getName() + ": Фиксированная цена " + getFixedPrice();
    }
}
