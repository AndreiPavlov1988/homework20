package org.skypro.skyshop;

import org.skypro.skyshop.product.*;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина с Stream API ===\n");

                // Создаем товары
                Product laptop = new SimpleProduct("Ноутбук игровой мощный", 75000);
                Product laptop2 = new SimpleProduct("Ноутбук игровой мощный", 80000); // Дубликат имени
                Product phone = new DiscountedProduct("Смартфон", 45000, 20);
                Product headphones = new FixPriceProduct("Наушники");
                Product mouse = new SimpleProduct("Мышь", 1500);
                Product keyboard = new DiscountedProduct("Клавиатура механическая", 3500, 10);
                Product tablet = new SimpleProduct("Планшет для работы и учебы", 30000);
                Product monitor = new SimpleProduct("Монитор", 20000);
                Product charger = new SimpleProduct("Зарядное устройство", 1000);

                // Создаем статьи
                Article laptopArticle = new Article(
                        "Обзор игрового ноутбука с мощной видеокартой",
                        "Игровой ноутбук обладает мощной видеокартой и процессором."
                );

                Article phoneArticle = new Article(
                        "Смартфоны",
                        "Обзор лучших смартфонов 2024 года."
                );

                Article headphonesArticle = new Article(
                        "Наушники для геймеров и музыки",
                        "Лучшие наушники для игр. Важен качественный звук."
                );

                Article gamingArticle = new Article(
                        "Игровая периферия",
                        "Мыши, клавиатуры, коврики для игр."
                );

                // Часть 1: Демонстрация поискового движка со Stream API
                System.out.println("=== Часть 1: Демонстрация SearchEngine с Stream API ===\n");

                SearchEngine searchEngine = new SearchEngine(20);

                // Добавляем объекты
                searchEngine.add(laptop);
                searchEngine.add(laptop2); // Будет проигнорирован (дубликат имени)
                searchEngine.add(phone);
                searchEngine.add(headphones);
                searchEngine.add(mouse);
                searchEngine.add(keyboard);
                searchEngine.add(tablet);
                searchEngine.add(monitor);
                searchEngine.add(charger);
                searchEngine.add(laptopArticle);
                searchEngine.add(phoneArticle);
                searchEngine.add(headphonesArticle);
                searchEngine.add(gamingArticle);

                System.out.println("В поисковый движок добавлено уникальных объектов: " + searchEngine.getCount());

                System.out.println("\n1. Поиск по слову 'игровой' (Stream API + TreeSet с компаратором):");
                Set<Searchable> results1 = searchEngine.search("игровой");
                System.out.println("   Найдено результатов: " + results1.size());

                int counter = 1;
                for (Searchable item : results1) {
                        System.out.println("   " + counter + ". \"" + item.getName() + "\" (длина: " +
                                item.getName().length() + " симв.) - " + item.getContentType());
                        counter++;
                }

                System.out.println("\n2. Поиск по слову 'наушники' (Stream API):");
                Set<Searchable> results2 = searchEngine.search("наушники");
                System.out.println("   Найдено результатов: " + results2.size());

                counter = 1;
                for (Searchable item : results2) {
                        System.out.println("   " + counter + ". \"" + item.getName() + "\" (длина: " +
                                item.getName().length() + " симв.)");
                        counter++;
                }

                // Часть 2: Демонстрация корзины со Stream API
                System.out.println("\n=== Часть 2: Демонстрация ProductBasket с Stream API ===\n");

                ProductBasket basket = new ProductBasket();

                System.out.println("3. Добавляем товары в корзину:");
                basket.addProduct(laptop);
                basket.addProduct(laptop2); // Второй ноутбук с таким же именем
                basket.addProduct(phone);
                basket.addProduct(headphones);
                basket.addProduct(mouse);
                basket.addProduct(keyboard);
                basket.addProduct(tablet);
                basket.addProduct(monitor);
                basket.addProduct(charger);

                System.out.println("   Уникальных имен товаров: " + basket.getUniqueProductNamesCount());
                System.out.println("   Всего товаров: " + basket.getTotalProductCount());

                System.out.println("\n4. Печать содержимого корзины (через Stream API forEach):");
                basket.printContents();

                System.out.println("\n5. Проверка методов корзины через Stream API:");
                System.out.println("   Общая стоимость: " + basket.getTotalPrice() + " руб.");
                System.out.println("   Содержит 'Смартфон': " + basket.containsProduct("Смартфон"));
                System.out.println("   Содержит 'Телевизор': " + basket.containsProduct("Телевизор"));

                System.out.println("\n6. Получение всех товаров через Stream API:");
                List<Product> allProducts = basket.getAllProducts();
                System.out.println("   Всего товаров в списке: " + allProducts.size());
                System.out.println("   Первые 3 товара:");
                allProducts.stream().limit(3).forEach(p -> System.out.println("   - " + p.getName()));

                System.out.println("\n7. Удаление товаров по имени:");
                System.out.println("   Удаляем 'Мышь':");
                List<Product> removed = basket.removeProductsByName("Мышь");
                System.out.println("   Удалено товаров: " + removed.size());

                System.out.println("\n   Содержимое корзины после удаления:");
                basket.printContents();

                // Часть 3: Дополнительная демонстрация Stream API
                System.out.println("\n=== Часть 3: Дополнительные возможности Stream API ===\n");

                System.out.println("8. Сортировка товаров по цене (Stream API демонстрация):");
                basket.getAllProducts().stream()
                        .sorted((p1, p2) -> Integer.compare(p2.getPrice(), p1.getPrice()))
                        .limit(3)
                        .forEach(p -> System.out.println("   " + p.getName() + ": " + p.getPrice() + " руб."));

                System.out.println("\n9. Фильтрация товаров дороже 20000 руб.:");
                long expensiveCount = basket.getAllProducts().stream()
                        .filter(p -> p.getPrice() > 20000)
                        .count();
                System.out.println("   Товаров дороже 20000 руб.: " + expensiveCount);

                System.out.println("\n10. Группировка товаров по типу (Special/Regular):");
                basket.getAllProducts().stream()
                        .collect(Collectors.groupingBy(
                                p -> p.isSpecial() ? "Специальные" : "Обычные",
                                Collectors.counting()
                        ))
                        .forEach((type, count) -> System.out.println("   " + type + ": " + count));

                System.out.println("\n=== Демонстрация завершена ===");
        }
}
