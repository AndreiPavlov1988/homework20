package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;
import java.util.Objects;

/**
 * Абстрактный класс товара - корень иерархии
 */
public abstract class Product implements Searchable {
    private final String name;

    /**
     * Конструктор товара
     * @param name название товара
     * @throws IllegalArgumentException если название невалидное
     */
    protected Product(String name) {
        validateName(name);
        this.name = name;
    }

    /**
     * Проверяет валидность названия товара
     * @param name название для проверки
     * @throws IllegalArgumentException если название невалидное
     */
    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Название товара не может быть null, пустой строкой или состоять только из пробелов"
            );
        }
    }

    /**
     * Получить название товара
     * @return название товара
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Получить термин для поиска
     * @return имя товара для поиска
     */
    @Override
    public String getSearchTerm() {
        return name;
    }

    /**
     * Получить тип контента
     * @return "PRODUCT"
     */
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    /**
     * Абстрактный метод получения цены товара
     * @return цена товара
     */
    public abstract int getPrice();

    /**
     * Проверяет, является ли товар специальным
     * @return true если товар специальный, false если обычный
     */
    public abstract boolean isSpecial();

    /**
     * Возвращает строковое представление товара
     * @return строковое представление
     */
    @Override
    public abstract String toString();

    /**
     * Переопределение equals - сравнивает только по имени
     * @param obj объект для сравнения
     * @return true если имена одинаковые
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(name, product.name);
    }

    /**
     * Переопределение hashCode - использует только имя
     * @return хеш-код на основе имени
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
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