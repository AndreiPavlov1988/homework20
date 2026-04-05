package org.skypro.skyshop;

import org.skypro.skyshop.product.*;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;
import java.util.Set;

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина с Set и компаратором ===\n");

                // Создаем товары с разной длиной имен
                Product laptop = new SimpleProduct("Ноутбук игровой мощный", 75000);
                Product phone = new DiscountedProduct("Смартфон", 45000, 20);
                Product headphones = new FixPriceProduct("Наушники");
                Product mouse = new SimpleProduct("Мышь", 1500);
                Product keyboard = new DiscountedProduct("Клавиатура механическая", 3500, 10);
                Product tablet = new SimpleProduct("Планшет для работы и учебы", 30000);
                Product monitor = new SimpleProduct("Монитор", 20000);
                Product charger = new SimpleProduct("Зарядное устройство", 1000);

                // Создаем статьи с разной длиной заголовков
                Article laptopArticle = new Article(
                        "Обзор игрового ноутбука с мощной видеокартой",
                        "Игровой ноутбук обладает мощной видеокартой и процессором. " +
                                "Идеально подходит для игр и работы с графикой."
                );

                Article phoneArticle = new Article(
                        "Смартфоны",
                        "Обзор лучших смартфонов 2024 года. Смартфоны стали мощнее и умнее."
                );

                Article headphonesArticle = new Article(
                        "Наушники для геймеров и музыки",
                        "Лучшие наушники для игр. Важен качественный звук и комфорт. " +
                                "Беспроводные наушники удобны для игр."
                );

                Article gamingArticle = new Article(
                        "Игровая периферия",
                        "Мыши, клавиатуры, коврики для игр."
                );

                Article monitorArticle = new Article(
                        "Выбор монитора",
                        "Как выбрать монитор для игр: частота обновления, время отклика."
                );

                // Часть 1: Демонстрация Set (уникальность)
                System.out.println("=== Часть 1: Демонстрация уникальности объектов в SearchEngine ===\n");

                SearchEngine searchEngine = new SearchEngine(20);

                System.out.println("1. Добавляем объекты в поисковый движок:");
                searchEngine.add(laptop);
                searchEngine.add(laptop); // Попытка добавить дубликат
                searchEngine.add(phone);
                searchEngine.add(phone); // Попытка добавить дубликат
                searchEngine.add(headphones);
                searchEngine.add(mouse);
                searchEngine.add(keyboard);
                searchEngine.add(tablet);
                searchEngine.add(monitor);
                searchEngine.add(charger);
                searchEngine.add(laptopArticle);
                searchEngine.add(laptopArticle); // Попытка добавить дубликат
                searchEngine.add(phoneArticle);
                searchEngine.add(headphonesArticle);
                searchEngine.add(gamingArticle);
                searchEngine.add(monitorArticle);

                System.out.println("   Реальное количество уникальных объектов: " + searchEngine.getCount());
                System.out.println("   (Дубликаты были проигнорированы благодаря equals/hashCode)");

                // Часть 2: Демонстрация сортировки
                System.out.println("\n=== Часть 2: Демонстрация сортировки результатов поиска ===\n");

                System.out.println("2. Поиск по слову 'игровой' (сортировка от длинного названия к короткому):");
                Set<Searchable> results1 = searchEngine.search("игровой");
                System.out.println("   Найдено результатов: " + results1.size());

                int counter = 1;
                for (Searchable item : results1) {
                        String name = item.getName();
                        System.out.println("   " + counter + ". \"" + name + "\" (длина: " + name.length() + " симв.) - " + item.getContentType());
                        counter++;
                }

                System.out.println("\n3. Поиск по слову 'наушники' (сортировка от длинного названия к короткому):");
                Set<Searchable> results2 = searchEngine.search("наушники");
                System.out.println("   Найдено результатов: " + results2.size());

                counter = 1;
                for (Searchable item : results2) {
                        String name = item.getName();
                        System.out.println("   " + counter + ". \"" + name + "\" (длина: " + name.length() + " симв.) - " + item.getContentType());
                        counter++;
                }

                System.out.println("\n4. Поиск по слову 'монитор' (сортировка от длинного названия к короткому):");
                Set<Searchable> results3 = searchEngine.search("монитор");
                System.out.println("   Найдено результатов: " + results3.size());

                counter = 1;
                for (Searchable item : results3) {
                        String name = item.getName();
                        System.out.println("   " + counter + ". \"" + name + "\" (длина: " + name.length() + " симв.) - " + item.getContentType());
                        // Для статей выводим отрывок
                        if (item.getContentType().equals("ARTICLE")) {
                                String content = item.getSearchTerm();
                                if (content.length() > 60) {
                                        System.out.println("      " + content.substring(0, 60) + "...");
                                }
                        }
                        counter++;
                }

                System.out.println("\n5. Поиск по слову 'смартфон' (сортировка от длинного названия к короткому):");
                Set<Searchable> results4 = searchEngine.search("смартфон");
                System.out.println("   Найдено результатов: " + results4.size());

                counter = 1;
                for (Searchable item : results4) {
                        String name = item.getName();
                        System.out.println("   " + counter + ". \"" + name + "\" (длина: " + name.length() + " симв.)");
                        counter++;
                }

                System.out.println("\n6. Поиск по слову 'игр' (проверка сортировки при одинаковой длине):");
                Set<Searchable> results5 = searchEngine.search("игр");
                System.out.println("   Найдено результатов: " + results5.size());

                counter = 1;
                for (Searchable item : results5) {
                        String name = item.getName();
                        System.out.println("   " + counter + ". \"" + name + "\" (длина: " + name.length() + " симв.) - " + item.getContentType());
                        counter++;
                }

                // Часть 3: Демонстрация equals/hashCode
                System.out.println("\n=== Часть 3: Демонстрация работы equals/hashCode ===\n");

                System.out.println("7. Проверка equals для продуктов:");
                Product laptop1 = new SimpleProduct("Ноутбук", 50000);
                Product laptop2 = new SimpleProduct("Ноутбук", 60000);
                Product laptop3 = new SimpleProduct("Другой ноутбук", 55000);

                System.out.println("   laptop1.equals(laptop2): " + laptop1.equals(laptop2) + " (должно быть true - одинаковые имена)");
                System.out.println("   laptop1.equals(laptop3): " + laptop1.equals(laptop3) + " (должно быть false - разные имена)");

                System.out.println("\n8. Проверка equals для статей:");
                Article article1 = new Article("Заголовок", "Текст статьи");
                Article article2 = new Article("Заголовок", "Другой текст");
                Article article3 = new Article("Другой заголовок", "Текст статьи");

                System.out.println("   article1.equals(article2): " + article1.equals(article2) + " (должно быть true - одинаковые заголовки)");
                System.out.println("   article1.equals(article3): " + article1.equals(article3) + " (должно быть false - разные заголовки)");

                System.out.println("\n9. Проверка hashCode:");
                System.out.println("   hashCode(laptop1): " + laptop1.hashCode());
                System.out.println("   hashCode(laptop2): " + laptop2.hashCode() + " (должен быть одинаковым)");
                System.out.println("   hashCode(laptop3): " + laptop3.hashCode() + " (должен отличаться)");

                // Часть 4: Проверка корзины (для совместимости)
                System.out.println("\n=== Часть 4: Проверка обратной совместимости корзины ===\n");

                ProductBasket basket = new ProductBasket();
                basket.addProduct(laptop);
                basket.addProduct(phone);
                basket.addProduct(headphones);
                basket.addProduct(mouse);

                System.out.println("Содержимое корзины:");
                basket.printContents();

                System.out.println("\n=== Демонстрация завершена ===");
        }
}
