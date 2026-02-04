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

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина с проверками и расширенным поиском ===\n");

                // Часть 1: Демонстрация проверок данных
                System.out.println("=== Часть 1: Демонстрация проверок данных ===");

                System.out.println("\n1. Попытка создания товаров с невалидными данными:");

                // Попытка создания товара с пустым названием
                try {
                        Product invalidProduct1 = new SimpleProduct("", 1000);
                        System.out.println("Успешно создан: " + invalidProduct1.getName());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании товара с пустым названием: " + e.getMessage());
                }

                // Попытка создания товара с названием из пробелов
                try {
                        Product invalidProduct2 = new SimpleProduct("   ", 1000);
                        System.out.println("Успешно создан: " + invalidProduct2.getName());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании товара с названием из пробелов: " + e.getMessage());
                }

                // Попытка создания товара с null названием
                try {
                        Product invalidProduct3 = new SimpleProduct(null, 1000);
                        System.out.println("Успешно создан: " + invalidProduct3.getName());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании товара с null названием: " + e.getMessage());
                }

                // Попытка создания товара с нулевой ценой
                try {
                        Product invalidProduct4 = new SimpleProduct("Товар", 0);
                        System.out.println("Успешно создан товар с ценой: " + invalidProduct4.getPrice());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании товара с нулевой ценой: " + e.getMessage());
                }

                // Попытка создания товара с отрицательной ценой
                try {
                        Product invalidProduct5 = new SimpleProduct("Товар", -100);
                        System.out.println("Успешно создан товар с ценой: " + invalidProduct5.getPrice());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании товара с отрицательной ценой: " + e.getMessage());
                }

                // Попытка создания товара со скидкой с невалидной скидкой
                try {
                        Product invalidProduct6 = new DiscountedProduct("Товар", 1000, 150);
                        System.out.println("Успешно создан товар со скидкой: " + invalidProduct6.getPrice());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании товара с невалидной скидкой: " + e.getMessage());
                }

                // Попытка создания товара со скидкой с отрицательной скидкой
                try {
                        Product invalidProduct7 = new DiscountedProduct("Товар", 1000, -10);
                        System.out.println("Успешно создан товар со скидкой: " + invalidProduct7.getPrice());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании товара с отрицательной скидкой: " + e.getMessage());
                }

                // Попытка создания статьи с пустым заголовком
                try {
                        Article invalidArticle1 = new Article("", "Текст статьи");
                        System.out.println("Успешно создана статья: " + invalidArticle1.getTitle());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании статьи с пустым заголовком: " + e.getMessage());
                }

                // Попытка создания статьи с пустым текстом
                try {
                        Article invalidArticle2 = new Article("Заголовок", "");
                        System.out.println("Успешно создана статья с текстом: " + invalidArticle2.getContent());
                } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании статьи с пустым текстом: " + e.getMessage());
                }

                System.out.println("\n2. Создание валидных товаров и статей:");

                // Создаем валидные товары
                Product laptop = new SimpleProduct("Ноутбук игровой мощный", 75000);
                Product discountedPhone = new DiscountedProduct("Смартфон смартфон смартфон", 45000, 20);
                Product fixPriceHeadphones = new FixPriceProduct("Наушники беспроводные");
                Product mouse = new SimpleProduct("Мышь игровая", 1500);
                Product discountedKeyboard = new DiscountedProduct("Клавиатура механическая", 3500, 10);

                // Создаем валидные статьи
                Article laptopArticle = new Article(
                        "Обзор игрового ноутбука",
                        "Игровой ноутбук обладает мощной видеокартой и процессором. " +
                                "Идеально подходит для игр и работы с графикой. Ноутбук ноутбук ноутбук."
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

                System.out.println("Успешно создано 5 товаров и 4 статьи");

                // Часть 2: Демонстрация расширенного поиска
                System.out.println("\n=== Часть 2: Демонстрация расширенного поиска ===");

                // Создаем поисковый движок
                SearchEngine searchEngine = new SearchEngine(20);

                // Добавляем все товары и статьи
                searchEngine.add(laptop);
                searchEngine.add(discountedPhone);
                searchEngine.add(fixPriceHeadphones);
                searchEngine.add(mouse);
                searchEngine.add(discountedKeyboard);
                searchEngine.add(laptopArticle);
                searchEngine.add(phoneArticle);
                searchEngine.add(headphonesArticle);
                searchEngine.add(gamingArticle);

                System.out.println("\n3. Тестирование метода findBestMatch():");

                // Тест 1: Поиск с несколькими вхождениями
                System.out.println("\nТест 1: Поиск 'смартфон' (несколько вхождений в названии товара):");
                try {
                        Searchable bestMatch1 = searchEngine.findBestMatch("смартфон");
                        System.out.println("Лучший результат: " + bestMatch1.getStringRepresentation());
                        System.out.println("Тип: " + bestMatch1.getContentType());
                } catch (BestResultNotFound e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                // Тест 2: Поиск с вхождениями в статье
                System.out.println("\nТест 2: Поиск 'игровой' (вхождения в разных объектах):");
                try {
                        Searchable bestMatch2 = searchEngine.findBestMatch("игровой");
                        System.out.println("Лучший результат: " + bestMatch2.getStringRepresentation());
                        System.out.println("Тип: " + bestMatch2.getContentType());
                } catch (BestResultNotFound e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                // Тест 3: Поиск с максимальным количеством вхождений
                System.out.println("\nТест 3: Поиск 'ноутбук' (много вхождений в статье):");
                try {
                        Searchable bestMatch3 = searchEngine.findBestMatch("ноутбук");
                        System.out.println("Лучший результат: " + bestMatch3.getStringRepresentation());
                        System.out.println("Тип: " + bestMatch3.getContentType());
                } catch (BestResultNotFound e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                // Тест 4: Поиск несуществующего слова
                System.out.println("\nТест 4: Поиск 'книга' (нет вхождений):");
                try {
                        Searchable bestMatch4 = searchEngine.findBestMatch("книга");
                        System.out.println("Лучший результат: " + bestMatch4.getStringRepresentation());
                } catch (BestResultNotFound e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                // Тест 5: Поиск по подстроке
                System.out.println("\nТест 5: Поиск 'игр' (подстрока):");
                try {
                        Searchable bestMatch5 = searchEngine.findBestMatch("игр");
                        System.out.println("Лучший результат: " + bestMatch5.getStringRepresentation());
                        System.out.println("Тип: " + bestMatch5.getContentType());
                } catch (BestResultNotFound e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                // Тест 6: Поиск в пустом движке
                System.out.println("\nТест 6: Поиск в пустом поисковом движке:");
                SearchEngine emptyEngine = new SearchEngine(10);
                try {
                        Searchable bestMatch6 = emptyEngine.findBestMatch("тест");
                        System.out.println("Лучший результат: " + bestMatch6.getStringRepresentation());
                } catch (BestResultNotFound e) {
                        System.out.println("Ошибка: " + e.getMessage());
                }

                // Часть 3: Демонстрация старого функционала (для проверки обратной совместимости)
                System.out.println("\n=== Часть 3: Проверка старого функционала ===");

                System.out.println("\n4. Проверка работы корзины:");
                ProductBasket basket = new ProductBasket();
                basket.addProduct(laptop);
                basket.addProduct(discountedPhone);
                basket.addProduct(fixPriceHeadphones);

                System.out.println("Содержимое корзины:");
                basket.printContents();

                System.out.println("\n5. Проверка обычного поиска:");
                Searchable[] results = searchEngine.search("беспроводные");
                System.out.println("Результаты поиска по 'беспроводные':");
                for (int i = 0; i < results.length; i++) {
                        if (results[i] != null) {
                                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
                        }
                }

                System.out.println("\n=== Демонстрация завершена ===");
        }
}
