package org.skypro.skyshop;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина с поиском ===\n");

                // Создаем товары разных типов
                Product laptop = new SimpleProduct("Ноутбук Lenovo", 75000);
                Product discountedPhone = new DiscountedProduct("Смартфон Samsung", 45000, 20);
                Product fixPriceHeadphones = new FixPriceProduct("Наушники Sony");
                Product mouse = new SimpleProduct("Мышь беспроводная", 1500);
                Product discountedKeyboard = new DiscountedProduct("Клавиатура механическая", 3500, 10);

                // Создаем статьи о товарах
                Article laptopArticle = new Article(
                        "Обзор нового ноутбука Lenovo",
                        "Новый ноутбук Lenovo обладает мощным процессором и длительным временем работы от батареи. " +
                                "Идеально подходит для работы и учебы."
                );

                Article phoneArticle = new Article(
                        "Сравнение смартфонов Samsung и Apple",
                        "В этой статье мы сравниваем флагманские модели Samsung и Apple. " +
                                "Рассматриваем камеры, производительность и срок работы от батареи."
                );

                Article headphonesArticle = new Article(
                        "Как выбрать наушники для музыки",
                        "Руководство по выбору наушников: типы, характеристики, рекомендации. " +
                                "Рассматриваем проводные и беспроводные модели."
                );

                Article gamingArticle = new Article(
                        "Лучшая игровая периферия 2024",
                        "Обзор лучшей игровой периферии: мыши, клавиатуры, коврики. " +
                                "Рекомендации для геймеров разного уровня."
                );

                // Создаем поисковый движок
                SearchEngine searchEngine = new SearchEngine(20);

                // Добавляем все товары в поисковый движок
                searchEngine.add(laptop);
                searchEngine.add(discountedPhone);
                searchEngine.add(fixPriceHeadphones);
                searchEngine.add(mouse);
                searchEngine.add(discountedKeyboard);

                // Добавляем статьи в поисковый движок
                searchEngine.add(laptopArticle);
                searchEngine.add(phoneArticle);
                searchEngine.add(headphonesArticle);
                searchEngine.add(gamingArticle);

                System.out.println("В поисковый движок добавлено объектов: " + searchEngine.getCount());
                System.out.println("Емкость поискового движка: " + searchEngine.getCapacity());
                System.out.println();

                // Демонстрация поиска
                System.out.println("=== Тестирование поиска ===");

                System.out.println("\n1. Поиск по слову 'ноутбук':");
                Searchable[] results1 = searchEngine.search("ноутбук");
                printSearchResults(results1);

                System.out.println("\n2. Поиск по слову 'Samsung':");
                Searchable[] results2 = searchEngine.search("Samsung");
                printSearchResults(results2);

                System.out.println("\n3. Поиск по слову 'игровой':");
                Searchable[] results3 = searchEngine.search("игровой");
                printSearchResults(results3);

                System.out.println("\n4. Поиск по слову 'беспроводная':");
                Searchable[] results4 = searchEngine.search("беспроводная");
                printSearchResults(results4);

                System.out.println("\n5. Поиск по слову 'камера':");
                Searchable[] results5 = searchEngine.search("камера");
                printSearchResults(results5);

                System.out.println("\n6. Поиск по слову 'xyz' (нет результатов):");
                Searchable[] results6 = searchEngine.search("xyz");
                printSearchResults(results6);

                // Демонстрация работы с корзиной (из предыдущей домашки)
                System.out.println("\n=== Демонстрация работы корзины ===");
                ProductBasket basket = new ProductBasket();
                basket.addProduct(laptop);
                basket.addProduct(discountedPhone);
                basket.addProduct(fixPriceHeadphones);
                basket.addProduct(mouse);

                System.out.println("\nСодержимое корзины:");
                basket.printContents();

                // Демонстрация метода getStringRepresentation()
                System.out.println("\n=== Демонстрация getStringRepresentation() ===");
                System.out.println("Товар: " + laptop.getStringRepresentation());
                System.out.println("Статья: " + laptopArticle.getStringRepresentation());

                System.out.println("\n=== Демонстрация завершена ===");
        }

        /**
         * Выводит результаты поиска
         * @param results массив результатов поиска
         */
        private static void printSearchResults(Searchable[] results) {
                boolean hasResults = false;

                for (int i = 0; i < results.length; i++) {
                        if (results[i] != null) {
                                hasResults = true;
                                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());

                                // Для статей выводим еще и отрывок текста
                                if (results[i].getContentType().equals("ARTICLE")) {
                                        String content = results[i].getSearchTerm();
                                        // Выводим первые 50 символов текста
                                        if (content.length() > 50) {
                                                System.out.println("   " + content.substring(0, 50) + "...");
                                        } else {
                                                System.out.println("   " + content);
                                        }
                                }
                        }
                }

                if (!hasResults) {
                        System.out.println("Ничего не найдено");
                }
        }
}
