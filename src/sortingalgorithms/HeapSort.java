
package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HeapSort implements Sorter {


    private void makeHeap(Bar[] arr) {

        Bar[] heap = new Bar[arr.length];

        Arrays.setAll(heap, i -> new Bar(0, 0, 0, 0));

        for (int i = 0; i < arr.length; i++) {
            addToHeap(heap, i, arr[i]);
        }

        System.arraycopy(heap, 0, arr, 0, heap.length);

    }

    private void addToHeap(Bar[] heap, int size, Bar value) {

        heap[size] = value;
        heapifyUp(heap, size);
    }

    private void removeRoot(Bar[] arr, int heapSize) {
        Bar temp = arr[0];
        arr[0] = arr[heapSize - 1];
        arr[heapSize - 1] = temp;
    }

    private void heapifyUp(Bar[] arr, int parentIndex) {

        Bar leftChild = 2 * parentIndex + 1 < arr.length ? arr[2 * parentIndex + 1] : new Bar(0, 0, 0, 0);
        Bar rightChild = 2 * parentIndex + 2 < arr.length ? arr[2 * parentIndex + 2] : new Bar(0, 0, 0, 0);

        Bar maxChild = leftChild;
        if (rightChild.compareTo(leftChild) > 0) {
            maxChild = rightChild;
        }

        if (maxChild.compareTo(arr[parentIndex]) > 0) {

            Bar temp = arr[parentIndex];

            if (leftChild.compareTo(rightChild) > 0) {
                arr[parentIndex] = leftChild;
                arr[2 * parentIndex + 1] = temp;

            }
            if (rightChild.compareTo(leftChild) > 0) {
                arr[parentIndex] = rightChild;
                arr[2 * parentIndex + 2] = temp;
            }

        }

        if (parentIndex != 0) {
            heapifyUp(arr, (parentIndex - 1) / 2);
        }

    }

    private void sort(Bar[] arr) {

        makeHeap(arr);

        for (int i = arr.length; i >= 1; i--) {
            removeRoot(arr, i);
            heapifyBottom(arr, i - 1, 0);
        }

    }

    private void heapifyBottom(Bar[] arr, int heapSize, int index) {

        if (index >= heapSize) {
            return;
        }

        Bar left = 2 * index + 1 < heapSize ? arr[2 * index + 1] : new Bar(0, 0, 0, 0);
        Bar right = 2 * index + 2 < heapSize ? arr[2 * index + 2] : new Bar(0, 0, 0, 0);

        Bar maxChild = left;
        if (right.compareTo(left) > 0) {
            maxChild = right;
        }


        if (maxChild.compareTo(arr[index]) > 0) {

            Bar temp = arr[index];

            if (left.compareTo(right) >= 0) {
                arr[index] = left;
                arr[2 * index + 1] = temp;
                heapifyBottom(arr, heapSize, 2 * index + 1);
            } else {
                arr[index] = right;
                arr[2 * index + 2] = temp;
                heapifyBottom(arr, heapSize, 2 * index + 2);
            }
        }

    }


    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {
        sort(bars);
    }

}

