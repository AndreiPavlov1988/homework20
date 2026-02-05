package org.skypro.skyshop;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина с коллекциями ===\n");

                // Создаем валидные товары
                Product laptop1 = new SimpleProduct("Ноутбук игровой", 75000);
                Product laptop2 = new SimpleProduct("Ноутбук игровой", 80000); // Второй такой же
                Product discountedPhone = new DiscountedProduct("Смартфон Samsung", 45000, 20);
                Product fixPriceHeadphones = new FixPriceProduct("Наушники беспроводные");
                Product mouse = new SimpleProduct("Мышь игровая", 1500);
                Product discountedKeyboard = new DiscountedProduct("Клавиатура механическая", 3500, 10);
                Product tablet = new SimpleProduct("Планшет", 30000);
                Product monitor = new SimpleProduct("Монитор", 20000);

                // Создаем валидные статьи
                Article laptopArticle = new Article(
                        "Обзор игрового ноутбука",
                        "Игровой ноутбук обладает мощной видеокартой и процессором. " +
                                "Идеально подходит для игр и работы с графикой."
                );

                Article phoneArticle = new Article(
                        "Смартфоны 2024 года",
                        "Обзор лучших смартфонов 2024 года. Смартфоны стали мощнее и умнее. " +
                                "Рассматриваем камеры, производительность и батарею."
                );

                Article headphonesArticle = new Article(
                        "Наушники для геймеров",
                        "Лучшие наушники для игр. Важен качественный звук и комфорт. " +
                                "Беспроводные наушники удобны для игр."
                );

                Article gamingArticle = new Article(
                        "Игровая периферия",
                        "Мыши, клавиатуры, коврики для игр. Игровая мышь должна быть точной. " +
                                "Игровая клавиатура - отзывчивой."
                );

                // Часть 1: Демонстрация корзины со списком
                System.out.println("=== Часть 1: Демонстрация корзины с использованием списка ===\n");

                ProductBasket basket = new ProductBasket();

                System.out.println("1. Добавляем товары в корзину:");
                basket.addProduct(laptop1);
                basket.addProduct(laptop2); // Два одинаковых ноутбука
                basket.addProduct(discountedPhone);
                basket.addProduct(fixPriceHeadphones);
                basket.addProduct(mouse);
                basket.addProduct(discountedKeyboard);
                basket.addProduct(tablet);
                basket.addProduct(monitor);

                System.out.println("   В корзине теперь: " + basket.getProductCount() + " товаров\n");

                System.out.println("2. Печать содержимого корзины:");
                basket.printContents();

                System.out.println("\n3. Демонстрация метода removeProductsByName():");

                // Удаляем существующий продукт (оба ноутбука)
                System.out.println("\n   Удаление продукта 'Ноутбук игровой':");
                List<Product> removedProducts = basket.removeProductsByName("Ноутбук игровой");

                if (!removedProducts.isEmpty()) {
                        System.out.println("   Удаленные продукты:");
                        for (Product product : removedProducts) {
                                System.out.println("   - " + product.toString());
                        }
                        System.out.println("   Удалено товаров: " + removedProducts.size());
                } else {
                        System.out.println("   Список пуст - товары не найдены");
                }

                System.out.println("\n   Содержимое корзины после удаления:");
                basket.printContents();

                // Удаляем несуществующий продукт
                System.out.println("\n   Удаление несуществующего продукта 'Телевизор':");
                List<Product> removedProducts2 = basket.removeProductsByName("Телевизор");

                if (!removedProducts2.isEmpty()) {
                        System.out.println("   Удаленные продукты:");
                        for (Product product : removedProducts2) {
                                System.out.println("   - " + product.toString());
                        }
                } else {
                        System.out.println("   Список пуст - товары не найдены");
                }

                System.out.println("\n   Содержимое корзины после попытки удаления несуществующего товара:");
                basket.printContents();

                // Часть 2: Демонстрация SearchEngine со списком
                System.out.println("\n=== Часть 2: Демонстрация поискового движка с использованием списка ===\n");

                SearchEngine searchEngine = new SearchEngine(20);

                // Добавляем все товары и статьи
                searchEngine.add(laptop1);
                searchEngine.add(laptop2);
                searchEngine.add(discountedPhone);
                searchEngine.add(fixPriceHeadphones);
                searchEngine.add(mouse);
                searchEngine.add(discountedKeyboard);
                searchEngine.add(tablet);
                searchEngine.add(monitor);
                searchEngine.add(laptopArticle);
                searchEngine.add(phoneArticle);
                searchEngine.add(headphonesArticle);
                searchEngine.add(gamingArticle);

                System.out.println("В поисковый движок добавлено объектов: " + searchEngine.getCount());

                System.out.println("\n4. Тестирование поиска (возвращает все результаты):");

                // Поиск по слову 'игровой'
                System.out.println("\n   Поиск по слову 'игровой':");
                List<Searchable> results1 = searchEngine.search("игровой");
                System.out.println("   Найдено результатов: " + results1.size());
                for (int i = 0; i < results1.size(); i++) {
                        System.out.println("   " + (i + 1) + ". " + results1.get(i).getStringRepresentation());
                }

                // Поиск по слову 'смартфон'
                System.out.println("\n   Поиск по слову 'смартфон':");
                List<Searchable> results2 = searchEngine.search("смартфон");
                System.out.println("   Найдено результатов: " + results2.size());
                for (int i = 0; i < results2.size(); i++) {
                        System.out.println("   " + (i + 1) + ". " + results2.get(i).getStringRepresentation());
                }

                // Поиск по слову 'беспроводные'
                System.out.println("\n   Поиск по слову 'беспроводные':");
                List<Searchable> results3 = searchEngine.search("беспроводные");
                System.out.println("   Найдено результатов: " + results3.size());
                for (int i = 0; i < results3.size(); i++) {
                        System.out.println("   " + (i + 1) + ". " + results3.get(i).getStringRepresentation());
                }

                // Поиск по слову, которого нет
                System.out.println("\n   Поиск по слову 'автомобиль':");
                List<Searchable> results4 = searchEngine.search("автомобиль");
                System.out.println("   Найдено результатов: " + results4.size());
                if (results4.isEmpty()) {
                        System.out.println("   Ничего не найдено");
                }

                System.out.println("\n5. Тестирование метода findBestMatch():");

                // Тест метода findBestMatch
                System.out.println("\n   Поиск лучшего результата для 'игровой':");
                try {
                        Searchable bestMatch = searchEngine.findBestMatch("игровой");
                        System.out.println("   Лучший результат: " + bestMatch.getStringRepresentation());
                } catch (BestResultNotFound e) {
                        System.out.println("   Ошибка: " + e.getMessage());
                }

                // Часть 3: Демонстрация большого количества товаров
                System.out.println("\n=== Часть 3: Демонстрация работы с большим количеством товаров ===\n");

                ProductBasket largeBasket = new ProductBasket();

                // Добавляем много товаров
                for (int i = 1; i <= 15; i++) {
                        largeBasket.addProduct(new SimpleProduct("Товар " + i, 100 * i));
                }

                System.out.println("В корзину добавлено " + largeBasket.getProductCount() + " товаров");
                System.out.println("Общая стоимость: " + largeBasket.getTotalPrice() + " руб.");

                // Удаляем несколько товаров
                System.out.println("\n   Удаление товаров с 1 по 5:");
                int totalRemoved = 0;
                for (int i = 1; i <= 5; i++) {
                        List<Product> removed = largeBasket.removeProductsByName("Товар " + i);
                        totalRemoved += removed.size();
                }
                System.out.println("   Удалено товаров: " + totalRemoved);
                System.out.println("   Осталось товаров: " + largeBasket.getProductCount());
                System.out.println("   Новая общая стоимость: " + largeBasket.getTotalPrice() + " руб.");

                System.out.println("\n=== Демонстрация завершена ===");
        }
}
