package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина ===\n");

                // Создаем несколько продуктов
                Product laptop = new Product("Ноутбук", 75000);
                Product phone = new Product("Смартфон", 45000);
                Product headphones = new Product("Наушники", 5000);
                Product mouse = new Product("Мышь", 1500);
                Product keyboard = new Product("Клавиатура", 3500);
                Product tablet = new Product("Планшет", 30000); // Для демонзации переполнения

                // Создаем корзину
                ProductBasket basket = new ProductBasket();

                System.out.println("1. Добавление продукта в корзину:");
                basket.addProduct(laptop);
                System.out.println("   Добавлен: " + laptop.getName() + "\n");

                System.out.println("2. Добавление продуктов до заполнения корзины:");
                basket.addProduct(phone);
                basket.addProduct(headphones);
                basket.addProduct(mouse);
                basket.addProduct(keyboard);
                System.out.println("   Корзина заполнена\n");

                System.out.println("3. Попытка добавления продукта в заполненную корзину:");
                basket.addProduct(tablet);
                System.out.println();

                System.out.println("4. Печать содержимого корзины с несколькими товарами:");
                basket.printContents();
                System.out.println();

                System.out.println("5. Получение стоимости корзины с несколькими товарами:");
                System.out.println("   Общая стоимость: " + basket.getTotalPrice() + " руб.\n");

                System.out.println("6. Поиск товара, который есть в корзине:");
                String searchName1 = "Смартфон";
                boolean found1 = basket.containsProduct(searchName1);
                System.out.println("   Товар '" + searchName1 + "' в корзине: " + found1 + "\n");

                System.out.println("7. Поиск товара, которого нет в корзине:");
                String searchName2 = "Телевизор";
                boolean found2 = basket.containsProduct(searchName2);
                System.out.println("   Товар '" + searchName2 + "' в корзине: " + found2 + "\n");

                System.out.println("8. Очистка корзины:");
                basket.clear();
                System.out.println("   Корзина очищена\n");

                System.out.println("9. Печать содержимого пустой корзины:");
                basket.printContents();
                System.out.println();

                System.out.println("10. Получение стоимости пустой корзины:");
                System.out.println("    Общая стоимость: " + basket.getTotalPrice() + " руб.\n");

                System.out.println("11. Поиск товара по имени в пустой корзине:");
                String searchName3 = "Ноутбук";
                boolean found3 = basket.containsProduct(searchName3);
                System.out.println("    Товар '" + searchName3 + "' в корзине: " + found3 + "\n");

                System.out.println("=== Демонстрация завершена ===");

                // Дополнительная демонстрация с другой корзиной
                System.out.println("\n=== Дополнительная демонстрация ===");

                ProductBasket basket2 = new ProductBasket();
                Product apple = new Product("Яблоки", 100);
                Product bread = new Product("Хлеб", 50);

                basket2.addProduct(apple);
                basket2.addProduct(bread);

                System.out.println("Содержимое второй корзины:");
                basket2.printContents();
        }
}
