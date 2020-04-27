package sortingalgorithms;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements ComparativeSorter<T> {

    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(T[] arr, int start, int end) {

        if (start != end) {

            sort(arr, start, (start + end) / 2);
            sort(arr, (start + end) / 2 + 1, end);

            merge(arr, start, (start + end) / 2, (start + end) / 2 + 1, end);

        }

    }

    private void merge(T[] arr, int a, int b, int x, int y) {


        List<T> sorte = new ArrayList<>((b - a + 1) + (y - x + 1));
        int size = 0;

        int l = a;
        int r = x;

        while (l <= b && r <= y) {
            if (arr[r].compareTo(arr[l]) <= 0) {
                sorte.add(arr[r++]);
                size++;
            }
            else {
                sorte.add(arr[l++]);
            }
        }

        for (int i = l; i <= b; ++i) {
            sorte.add(arr[i]);
            size++;
        }

        for (int i = r; i <= y; ++i) {
            sorte.add(arr[i]);
        }

        size = 0;

        for (int i = a; i <= y; ++i) {
            arr[i] = sorte.get(size++);
        }

    }
}
