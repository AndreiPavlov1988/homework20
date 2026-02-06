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
import java.util.Map;

public class App {
        public static void main(String[] args) {
                System.out.println("=== Демонстрация работы интернет-магазина с Map коллекциями ===\n");

                // Создаем валидные товары (некоторые с одинаковыми именами)
                Product laptop1 = new SimpleProduct("Ноутбук игровой", 75000);
                Product laptop2 = new SimpleProduct("Ноутбук игровой", 80000); // Второй такой же
                Product discountedPhone = new DiscountedProduct("Смартфон Samsung", 45000, 20);
                Product fixPriceHeadphones = new FixPriceProduct("Наушники беспроводные");
                Product mouse1 = new SimpleProduct("Мышь игровая", 1500);
                Product mouse2 = new SimpleProduct("Мышь игровая", 2000); // Вторая такая же
                Product discountedKeyboard = new DiscountedProduct("Клавиатура механическая", 3500, 10);
                Product tablet = new SimpleProduct("Планшет", 30000);
                Product monitor = new SimpleProduct("Монитор", 20000);
                Product anotherPhone = new DiscountedProduct("Смартфон Samsung", 50000, 15); // Еще один смартфон

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

                Article monitorArticle = new Article(
                        "Выбор монитора для игр",
                        "Как выбрать монитор для игр: частота обновления, время отклика, разрешение. " +
                                "Советы по выбору игрового монитора."
                );

                // Часть 1: Демонстрация корзины с Map
                System.out.println("=== Часть 1: Демонстрация корзины с использованием Map ===\n");

                ProductBasket basket = new ProductBasket();

                System.out.println("1. Добавляем товары в корзину (включая дубликаты):");
                basket.addProduct(laptop1);
                basket.addProduct(laptop2); // Два одинаковых ноутбука
                basket.addProduct(discountedPhone);
                basket.addProduct(fixPriceHeadphones);
                basket.addProduct(mouse1);
                basket.addProduct(mouse2); // Две одинаковые мыши
                basket.addProduct(discountedKeyboard);
                basket.addProduct(tablet);
                basket.addProduct(monitor);
                basket.addProduct(anotherPhone); // Еще один смартфон Samsung

                System.out.println("   Уникальных имен товаров: " + basket.getUniqueProductNamesCount());
                System.out.println("   Всего товаров: " + basket.getTotalProductCount());

                System.out.println("\n2. Печать содержимого корзины (автоматически сортируется по имени):");
                basket.printContents();

                System.out.println("\n3. Получение товаров по имени:");
                System.out.println("   Товары с именем 'Смартфон Samsung':");
                List<Product> samsungPhones = basket.getProductsByName("Смартфон Samsung");
                for (Product phone : samsungPhones) {
                        System.out.println("   - " + phone.toString());
                }
                System.out.println("   Найдено: " + samsungPhones.size() + " товаров");

                System.out.println("\n   Товары с именем 'Мышь игровая':");
                List<Product> mice = basket.getProductsByName("Мышь игровая");
                for (Product mouse : mice) {
                        System.out.println("   - " + mouse.toString());
                }
                System.out.println("   Найдено: " + mice.size() + " товаров");

                System.out.println("\n4. Демонстрация метода removeProductsByName():");

                // Удаляем существующий продукт (все смартфоны Samsung)
                System.out.println("\n   Удаление всех товаров 'Смартфон Samsung':");
                List<Product> removedPhones = basket.removeProductsByName("Смартфон Samsung");

                if (!removedPhones.isEmpty()) {
                        System.out.println("   Удаленные смартфоны:");
                        for (Product phone : removedPhones) {
                                System.out.println("   - " + phone.toString());
                        }
                        System.out.println("   Удалено смартфонов: " + removedPhones.size());
                } else {
                        System.out.println("   Список пуст - товары не найдены");
                }

                System.out.println("\n   Содержимое корзины после удаления смартфонов:");
                basket.printContents();

                // Удаляем несуществующий продукт
                System.out.println("\n   Удаление несуществующего продукта 'Телевизор':");
                List<Product> removedTvs = basket.removeProductsByName("Телевизор");

                if (!removedTvs.isEmpty()) {
                        System.out.println("   Удаленные телевизоры:");
                        for (Product tv : removedTvs) {
                                System.out.println("   - " + tv.toString());
                        }
                } else {
                        System.out.println("   Список пуст - товары не найдены");
                }

                System.out.println("\n   Содержимое корзины после попытки удаления несуществующего товара:");
                basket.printContents();

                // Часть 2: Демонстрация SearchEngine с отсортированным Map
                System.out.println("\n=== Часть 2: Демонстрация поискового движка с отсортированным Map ===\n");

                SearchEngine searchEngine = new SearchEngine(20);

                // Добавляем все товары и статьи
                searchEngine.add(laptop1);
                searchEngine.add(laptop2);
                searchEngine.add(discountedPhone);
                searchEngine.add(fixPriceHeadphones);
                searchEngine.add(mouse1);
                searchEngine.add(mouse2);
                searchEngine.add(discountedKeyboard);
                searchEngine.add(tablet);
                searchEngine.add(monitor);
                searchEngine.add(anotherPhone);
                searchEngine.add(laptopArticle);
                searchEngine.add(phoneArticle);
                searchEngine.add(headphonesArticle);
                searchEngine.add(gamingArticle);
                searchEngine.add(monitorArticle);

                System.out.println("В поисковый движок добавлено объектов: " + searchEngine.getCount());

                System.out.println("\n5. Тестирование поиска с возвратом отсортированного Map:");

                // Поиск по слову 'игровой'
                System.out.println("\n   Поиск по слову 'игровой' (результаты отсортированы по имени):");
                Map<String, Searchable> results1 = searchEngine.search("игровой");
                System.out.println("   Найдено результатов: " + results1.size());

                int counter = 1;
                for (Map.Entry<String, Searchable> entry : results1.entrySet()) {
                        System.out.println("   " + counter + ". Имя: " + entry.getKey() +
                                " | Тип: " + entry.getValue().getContentType());
                        counter++;
                }

                // Поиск по слову 'смартфон'
                System.out.println("\n   Поиск по слову 'смартфон' (результаты отсортированы по имени):");
                Map<String, Searchable> results2 = searchEngine.search("смартфон");
                System.out.println("   Найдено результатов: " + results2.size());

                counter = 1;
                for (Map.Entry<String, Searchable> entry : results2.entrySet()) {
                        Searchable item = entry.getValue();
                        System.out.println("   " + counter + ". " + item.getStringRepresentation());
                        counter++;
                }

                // Поиск по слову 'монитор'
                System.out.println("\n   Поиск по слову 'монитор' (результаты отсортированы по имени):");
                Map<String, Searchable> results3 = searchEngine.search("монитор");
                System.out.println("   Найдено результатов: " + results3.size());

                counter = 1;
                for (Map.Entry<String, Searchable> entry : results3.entrySet()) {
                        Searchable item = entry.getValue();
                        System.out.println("   " + counter + ". " + item.getStringRepresentation());
                        // Для статей выводим отрывок
                        if (item.getContentType().equals("ARTICLE")) {
                                String content = item.getSearchTerm();
                                if (content.length() > 60) {
                                        System.out.println("      " + content.substring(0, 60) + "...");
                                }
                        }
                        counter++;
                }

                // Поиск по слову, которого нет
                System.out.println("\n   Поиск по слову 'автомобиль':");
                Map<String, Searchable> results4 = searchEngine.search("автомобиль");
                System.out.println("   Найдено результатов: " + results4.size());
                if (results4.isEmpty()) {
                        System.out.println("   Ничего не найдено");
                }

                // Демонстрация обратной совместимости
                System.out.println("\n6. Демонстрация обратной совместимости (searchAsList):");
                System.out.println("\n   Поиск по слову 'игр' (в виде списка, отсортированного по имени):");
                List<Searchable> listResults = searchEngine.searchAsList("игр");
                System.out.println("   Найдено результатов: " + listResults.size());

                for (int i = 0; i < listResults.size(); i++) {
                        Searchable item = listResults.get(i);
                        System.out.println("   " + (i + 1) + ". " + item.getStringRepresentation());
                }

                System.out.println("\n7. Получение всех уникальных имен из корзины:");
                List<String> allProductNames = basket.getAllProductNames();
                System.out.println("   Уникальные имена товаров в корзине (отсортированные):");
                for (String name : allProductNames) {
                        System.out.println("   - " + name);
                }

                System.out.println("\n=== Демонстрация завершена ===");
        }
}
