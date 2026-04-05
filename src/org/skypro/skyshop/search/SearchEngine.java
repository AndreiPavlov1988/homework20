package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;


public class SearchEngine {
    private final Set<Searchable> searchables;


    public SearchEngine(int capacity) {
        this.searchables = new HashSet<>();
    }


    public void add(Searchable searchable) {
        searchables.add(searchable);
    }


    public Set<Searchable> search(String query) {
        return searchables.stream()
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new SearchableNameLengthComparator())));
    }


    public List<Searchable> searchAsList(String query) {
        return searchables.stream()
                .filter(searchable -> searchable.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .sorted(new SearchableNameLengthComparator())
                .collect(Collectors.toList());
    }


    public Searchable findBestMatch(String searchQuery) throws BestResultNotFound {
        if (searchables.isEmpty()) {
            throw new BestResultNotFound(searchQuery);
        }

        String query = searchQuery.toLowerCase();

        return searchables.stream()
                .max((s1, s2) -> {
                    int occurrences1 = countOccurrences(s1.getSearchTerm().toLowerCase(), query);
                    int occurrences2 = countOccurrences(s2.getSearchTerm().toLowerCase(), query);
                    return Integer.compare(occurrences1, occurrences2);
                })
                .filter(searchable -> countOccurrences(searchable.getSearchTerm().toLowerCase(), query) > 0)
                .orElseThrow(() -> new BestResultNotFound(searchQuery));
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
