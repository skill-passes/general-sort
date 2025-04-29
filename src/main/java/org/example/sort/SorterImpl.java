package org.example.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Provides stable sort implementation for Sorter Interface
 *
 * @param <T> type of elements in the list to be sorted
 */
public class SorterImpl<T> implements Sorter<T> {

    /**
     * Sorts the list according to the natural order.
     * This sort is guaranteed to be stable
     *
     * @param list the list to be sorted
     */
    @SuppressWarnings("unchecked")
    @Override
    public void sort(List<T> list) {
        sort(list, (Comparator<T>) Comparator.naturalOrder());
    }

    /**
     * Sorts the list according to the order induced by the specified comparator.
     * This sort is guaranteed to be stable.
     *
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order
     */
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) return;

        List<T> aux = new ArrayList<>(list);
        mergeSort(list, aux, 0, list.size() - 1, comparator);
    }

    /**
     * Recursively sorts a section of the list using the merge sort algorithm.
     *
     * @param list The list to be sorted
     * @param aux An auxiliary list of the same size used during merging
     * @param low The starting index of the section to be sorted (inclusive)
     * @param high The ending index of the section to be sorted (inclusive)
     * @param comparator The comparator used to determine the order of elements
     */
    private void mergeSort(List<T> list, List<T> aux, int low, int high, Comparator<T> comparator) {
        if (low >= high) return;

        int mid = low + (high - low) / 2;

        mergeSort(list, aux, low, mid, comparator);
        mergeSort(list, aux, mid + 1, high, comparator);
        merge(list, aux, low, mid, high, comparator);
    }

    /**
     * Merges two adjacent sorted sections of a list into one sorted section.
     *
     * @param list The list containing the sections to be merged
     * @param aux An auxiliary list used during the merge process
     * @param low The starting index of the first section (inclusive)
     * @param mid The ending index of the first section (inclusive)
     * @param high The ending index of the second section (inclusive)
     * @param comparator The comparator used to determine the order of elements
     */
    private void merge(List<T> list, List<T> aux, int low, int mid, int high, Comparator<T> comparator) {
        for (int k = low; k <= high; k++) {
            aux.set(k, list.get(k));
        }

        int i = low, j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid) list.set(k, aux.get(j++));
            else if (j > high) list.set(k, aux.get(i++));
            else if (comparator.compare(aux.get(i), aux.get(j)) <= 0) list.set(k, aux.get(i++));
            else list.set(k, aux.get(j++));
        }
    }
}
