package org.example.sort;

import java.util.Comparator;
import java.util.List;

public interface Sorter<T> {
    void sort(List<T> list, Comparator<T> comparator);
    void sort(List<T> list);
}
