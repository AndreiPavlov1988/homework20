package org.skypro.skyshop.search;

import java.util.*;

public class SearchEngine {
    private final List<Searchable> searchables;


    public SearchEngine(int capacity) {
        this.searchables = new ArrayList<>(capacity);
    }


    public void add(Searchable searchable) {
        searchables.add(searchable);
    }


    public Map<String, Searchable> search(String query) {
        // Используем TreeMap для автоматической сортировки по ключу (имени)
        Map<String, Searchable> results = new TreeMap<>();

        // Поиск по всем объектам
        for (Searchable searchable : searchables) {
            String searchTerm = searchable.getSearchTerm();

            // Поиск без учета регистра
            if (searchTerm.toLowerCase().contains(query.toLowerCase())) {
                // Добавляем в Map, ключ - имя объекта
                results.put(searchable.getName(), searchable);
            }
        }

        return results;
    }


    public List<Searchable> searchAsList(String query) {
        List<Searchable> results = new ArrayList<>();

        for (Searchable searchable : searchables) {
            String searchTerm = searchable.getSearchTerm();

            if (searchTerm.toLowerCase().contains(query.toLowerCase())) {
                results.add(searchable);
            }
        }

        // Сортируем результаты по имени
        results.sort(Comparator.comparing(Searchable::getName));
        return results;
    }

    public Searchable findBestMatch(String searchQuery) throws BestResultNotFound {
        if (searchables.isEmpty()) {
            throw new BestResultNotFound(searchQuery);
        }

        Searchable bestMatch = null;
        int maxOccurrences = -1;

        for (Searchable searchable : searchables) {
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

    public int getCount() {
        return searchables.size();
    }

    public List<Searchable> getAllSearchables() {
        return new ArrayList<>(searchables);
    }
}
