package org.skypro.skyshop.search;

import java.util.Comparator;

public class SearchableNameLengthComparator implements Comparator<Searchable> {

    @Override
    public int compare(Searchable o1, Searchable o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return 1;
        if (o2 == null) return -1;

        String name1 = o1.getName();
        String name2 = o2.getName();

        if (name1 == null && name2 == null) return 0;
        if (name1 == null) return 1;
        if (name2 == null) return -1;

        // Сравниваем по длине имени (от длинного к короткому)
        int lengthCompare = Integer.compare(name2.length(), name1.length());

        // Если длины одинаковые, сравниваем в натуральном порядке
        if (lengthCompare == 0) {
            return name1.compareTo(name2);
        }

        return lengthCompare;
    }
}
