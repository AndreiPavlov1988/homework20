package org.skypro.skyshop;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина с новыми типами товаров ===\n");

                // Создаем товары разных типов
                Product laptop = new SimpleProduct("Ноутбук", 75000);
                Product discountedPhone = new DiscountedProduct("Смартфон", 45000, 20); // 20% скидка
                Product fixPriceHeadphones = new FixPriceProduct("Наушники");
                Product mouse = new SimpleProduct("Мышь", 1500);
                Product discountedKeyboard = new DiscountedProduct("Клавиатура", 3500, 10); // 10% скидка
                Product fixPriceMousePad = new FixPriceProduct("Коврик для мыши");

                // Создаем корзину
                ProductBasket basket = new ProductBasket();

                System.out.println("1. Добавляем товары разных типов в корзину:");
                basket.addProduct(laptop);
                basket.addProduct(discountedPhone);
                basket.addProduct(fixPriceHeadphones);
                basket.addProduct(mouse);
                basket.addProduct(discountedKeyboard);
                System.out.println();

                System.out.println("2. Попытка добавления товара в заполненную корзину:");
                basket.addProduct(fixPriceMousePad); // Должно вывести "Невозможно добавить продукт"
                System.out.println();

                System.out.println("3. Печать содержимого корзины с разными типами товаров:");
                basket.printContents();
                System.out.println();

                System.out.println("4. Получение общей стоимости корзины:");
                System.out.println("   Общая стоимость: " + basket.getTotalPrice() + " руб.");
                System.out.println();

                System.out.println("5. Поиск товаров в корзине:");
                System.out.println("   Товар 'Смартфон' в корзине: " + basket.containsProduct("Смартфон"));
                System.out.println("   Товар 'Планшет' в корзине: " + basket.containsProduct("Планшет"));
                System.out.println();

                System.out.println("6. Очистка корзины:");
                basket.clear();
                System.out.println("   Корзина очищена");
                System.out.println();

                System.out.println("7. Печать содержимого пустой корзины:");
                basket.printContents();
                System.out.println();

                System.out.println("8. Создание и тестирование новой корзины со специальными товарами:");
                ProductBasket specialBasket = new ProductBasket();

                // Добавляем только специальные товары
                specialBasket.addProduct(new DiscountedProduct("Телевизор", 50000, 15));
                specialBasket.addProduct(new FixPriceProduct("Кабель USB"));
                specialBasket.addProduct(new DiscountedProduct("Холодильник", 80000, 25));

                System.out.println("Содержимое корзины со специальными товарами:");
                specialBasket.printContents();

                System.out.println("\n=== Демонстрация завершена ===\n");

                // Дополнительная проверка расчетов
                System.out.println("=== Проверка расчетов цен ===");
                DiscountedProduct testProduct = new DiscountedProduct("Тестовый товар", 1000, 30);
                System.out.println("Товар со скидкой 30% от 1000: " + testProduct.getPrice() + " руб.");
                System.out.println("Ожидаемый результат: 700 руб. (1000 * 0.7)");

                System.out.println("\nЗначение фиксированной цены: " + FixPriceProduct.getFixedPrice() + " руб.");
        }
}
