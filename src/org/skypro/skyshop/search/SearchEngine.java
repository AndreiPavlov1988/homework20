package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchables;
    private int count;


    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.count = 0;
    }


    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count] = searchable;
            count++;
        } else {
            System.out.println("Невозможно добавить объект: достигнут лимит поискового движка");
        }
    }


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


    public int getCount() {
        return count;
    }


    public int getCapacity() {
        return searchables.length;
    }
}
