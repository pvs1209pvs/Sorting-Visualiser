
package sortingalgorithms;

import javafx.animation.Animation;
import sortingalgorithms.ComparativeSorter;
import visuals.Bar;

import java.util.ArrayList;
import java.util.List;

public class HeapSort<T extends Comparable<T>> implements ComparativeSorter<T> {

    private List<T> minHeap;
    private int heapSize;

    public void sort(T[] arr) {

        minHeap = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            minHeap.add(null);
        }
        heapSize = 0;

        minHeap.set(0, arr[0]);
        heapSize++;

        for (int i = 1; i < arr.length; ++i) {
            add_to_heap(arr[i]);
        }

        for (int l = 0; l < arr.length; ++l) {
            arr[l] = remove_from_heap(0);
        }

    }

    private void add_to_heap(T v) {
        minHeap.set(heapSize, v);
        insertion_heapify(heapSize);
        heapSize++;
    }

    private void insertion_heapify(int c) {

        while ((c - 1) / 2 >= 0 &&
                minHeap.get((c - 1) / 2).compareTo(minHeap.get(c))>0) {
            swap(c, (c - 1) / 2);
            c = (c - 1) / 2;
        }

    }

    private T remove_from_heap(int ele_index) {

        T ele = minHeap.get(ele_index); // delete the element
        T last_ele = minHeap.get(--heapSize); // get the last element
        minHeap.set(ele_index, last_ele); // save the last ele at the deleted ele's place.

        if (last_ele.compareTo(minHeap.get((ele_index - 1) / 2)) < 0) {
            heapify_above(ele_index);
        }
        else {
            heapify_below(ele_index);
        }

        return ele;
    }

    private void heapify_below(int p) {

        int left = p * 2 + 1;
        int right = p * 2 + 2;
        int min = p;

        if (left >= heapSize) return;

        if (minHeap.get(left).compareTo(minHeap.get(right)) <= 0) min = left;
        if (minHeap.get(right).compareTo(minHeap.get(left)) < 0) min = right;

        if (p == min) return;

        if (minHeap.get(p).compareTo(minHeap.get(min)) > 0) {
            swap(p, min);
            heapify_below(min);
        }

    }

    private void heapify_above(int p) {

        if ((p - 1) / 2 < 0) {
            return;
        }
        else {
            if (minHeap.get(p).compareTo(minHeap.get((p - 1) / 2))<0) {
                swap(p, (p - 1) / 2);
                p = (p - 1) / 2;
                heapify_above(p);
            }
            else {
                return;
            }
        }

    }

    private void swap(int x, int y) {
        T temp = minHeap.get(x);
        minHeap.set(x, minHeap.get(y));
        minHeap.set(y, temp);
    }

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {
        // INCOMPLETE
    }
}

