package org.example.sort;

import java.util.Comparator;
import java.util.List;

public class SorterImpl<T> implements Sorter<T> {
    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            siftUp(list, comparator, i);
        }

        int endIndex = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            swap(list, 0, endIndex);
            endIndex--;
            siftDown(list, comparator, 0, endIndex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void sort(List<T> list) {
        sort(list, (Comparator<T>) Comparator.naturalOrder());
    }

    private void siftUp(List<T> list, Comparator<T> comparator, int index) {
        if (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (
                    comparator.compare(list.get(index), list.get(parentIndex)) > 0
            ) {
                swap(list, index, parentIndex);
                siftUp(list, comparator, parentIndex);
            }
        }
    }

    private void siftDown(List<T> list, Comparator<T> comparator, int index, int endIndex) {
        int childIndex;
        if (index * 2 + 2 <= endIndex) {
            childIndex = comparator.compare(
                    list.get(index * 2 + 1), list.get(index * 2 + 2)) >= 0 ?
                    index * 2 + 1 : index * 2 + 2;
        } else if (index * 2 + 1 <= endIndex) {
            childIndex = index * 2 + 1;
        } else {
            return;
        }

        if (
                childIndex <= endIndex &&
                comparator.compare(list.get(index), list.get(childIndex)) < 0
        ) {
            swap(list, index, childIndex);
            siftDown(list, comparator, childIndex, endIndex);
        }
    }

    private void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
