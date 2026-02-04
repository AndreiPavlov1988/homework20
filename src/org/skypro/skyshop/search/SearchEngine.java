package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchables;
    private int count;

    /**
     * Конструктор поискового движка
     * @param capacity максимальное количество объектов для поиска
     */
    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.count = 0;
    }

    /**
     * Добавляет объект для поиска
     * @param searchable объект для добавления
     */
    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count] = searchable;
            count++;
        } else {
            System.out.println("Невозможно добавить объект: достигнут лимит поискового движка");
        }
    }

    /**
     * Выполняет поиск по запросу
     * @param query строка для поиска
     * @return массив из до 5 найденных объектов
     */
    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultCount = 0;

        // Поиск по всем объектам
        for (int i = 0; i < count && resultCount < 5; i++) {
            Searchable searchable = searchables[i];
            String searchTerm = searchable.getSearchTerm();

            // Поиск без учета регистра
            if (searchTerm.toLowerCase().contains(query.toLowerCase())) {
                results[resultCount] = searchable;
                resultCount++;
            }
        }

        return results;
    }

    /**
     * Находит наиболее подходящий объект для поискового запроса
     * @param searchQuery поисковый запрос
     * @return наиболее подходящий объект
     * @throws BestResultNotFound если не найден подходящий объект
     */
    public Searchable findBestMatch(String searchQuery) throws BestResultNotFound {
        if (count == 0) {
            throw new BestResultNotFound(searchQuery);
        }

        Searchable bestMatch = null;
        int maxOccurrences = -1;

        for (int i = 0; i < count; i++) {
            Searchable searchable = searchables[i];
            String searchTerm = searchable.getSearchTerm().toLowerCase();
            String query = searchQuery.toLowerCase();

            // Подсчет количества вхождений подстроки
            int occurrences = countOccurrences(searchTerm, query);

            // Если нашли объект с большим количеством вхождений
            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                bestMatch = searchable;
            }
        }

        // Если не найдено ни одного вхождения
        if (maxOccurrences == 0) {
            throw new BestResultNotFound(searchQuery);
        }

        return bestMatch;
    }

    /**
     * Подсчитывает количество вхождений подстроки в строку
     * @param text текст для поиска
     * @param substring подстрока для подсчета
     * @return количество вхождений
     */
    private int countOccurrences(String text, String substring) {
        int count = 0;
        int index = 0;
        int substringIndex = text.indexOf(substring, index);

        while (substringIndex != -1) {
            count++;
            index = substringIndex + substring.length();
            substringIndex = text.indexOf(substring, index);
        }

        return count;
    }

    /**
     * Получить количество добавленных объектов
     * @return количество объектов
     */
    public int getCount() {
        return count;
    }

    /**
     * Получить максимальную емкость поискового движка
     * @return максимальное количество объектов
     */
    public int getCapacity() {
        return searchables.length;
    }
}
