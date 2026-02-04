package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {

    /**
     * Конструктор исключения
     * @param searchQuery поисковый запрос
     */
    public BestResultNotFound(String searchQuery) {
        super("Не найден подходящий результат для поискового запроса: \"" + searchQuery + "\"");
    }
}
