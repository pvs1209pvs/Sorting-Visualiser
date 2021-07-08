
package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.Arrays;
import java.util.List;

public class HeapSort implements Sorter {

    private void makeHeap(Bar[] arr, List<Animation> trans, int gap, double seconds) {

        Bar[] heap = new Bar[arr.length];

        Arrays.setAll(heap, i -> new Bar(0, 0, 0, 0));

        for (int i = 0; i < arr.length; i++) {
            addToHeap(heap, i, arr[i], trans, gap, seconds);
        }

        System.arraycopy(heap, 0, arr, 0, heap.length);

    }

    private void addToHeap(Bar[] heap, int size, Bar value, List<Animation> trans, int gap, double seconds) {
        heap[size] = value;
        heapifyUp(heap, size, trans, gap, seconds);
    }

    private void removeRoot(Bar[] arr, int heapSize, List<Animation> trans, int gap, double seconds) {
        Bar temp = arr[0];
        arr[0] = arr[heapSize - 1];
        arr[heapSize - 1] = temp;

        TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), arr[heapSize-1]);
        a.setByX((heapSize - 1) * gap);

        TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), arr[0]);
        b.setByX(-1 * (heapSize - 1) * gap);

        trans.add(a);
        trans.add(b);
    }

    private void heapifyUp(Bar[] arr, int parentIndex, List<Animation> trans, int gap, double seconds) {

        Bar leftChild = 2 * parentIndex + 1 < arr.length ? arr[2 * parentIndex + 1] : new Bar(0, 0, 0, 0);
        Bar rightChild = 2 * parentIndex + 2 < arr.length ? arr[2 * parentIndex + 2] : new Bar(0, 0, 0, 0);

        Bar maxChild = leftChild;
        if (rightChild.compareTo(leftChild) > 0) {
            maxChild = rightChild;
        }

        if (maxChild.compareTo(arr[parentIndex]) > 0) {

            Bar temp = arr[parentIndex];

            if (leftChild.compareTo(rightChild) > 0) {
                parentLeftSwap(arr, parentIndex, trans, gap, seconds, leftChild, temp);
            }

            if (rightChild.compareTo(leftChild) > 0) {
                parentRightSwap(arr, parentIndex, trans, gap, seconds, rightChild, temp);
            }

        }

        if (parentIndex != 0) {
            heapifyUp(arr, (parentIndex - 1) / 2, trans, gap, seconds);
        }

    }



    private void parentLeftSwap(Bar[] arr, int parentIndex, List<Animation> trans, int gap, double seconds, Bar leftChild, Bar temp) {
        arr[parentIndex] = leftChild;
        arr[2 * parentIndex + 1] = temp;

        TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), arr[2 * parentIndex + 1]);
        a.setByX((2 * parentIndex + 1 - parentIndex) * gap);

        TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), arr[parentIndex]);
        b.setByX((parentIndex - (2 * parentIndex + 1)) * gap);

        trans.add(a);
        trans.add(b);
    }

    private void parentRightSwap(Bar[] arr, int parentIndex, List<Animation> trans, int gap, double seconds, Bar rightChild, Bar temp) {

        arr[parentIndex] = rightChild;
        arr[2 * parentIndex + 2] = temp;

        TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), arr[2 * parentIndex + 2]);
        a.setByX((2 * parentIndex + 2 - parentIndex) * gap);

        TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), arr[parentIndex]);
        b.setByX((parentIndex - (2 * parentIndex + 2)) * gap);

        trans.add(a);
        trans.add(b);

    }

    private void heapifyBottom(Bar[] arr, int heapSize, int index, List<Animation> trans, int gap, double seconds) {

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
                parentLeftSwap(arr, index, trans, gap, seconds, left, temp);

                heapifyBottom(arr, heapSize, 2 * index + 1, trans, gap, seconds);
            } else {
                parentRightSwap(arr, index, trans, gap, seconds, right, temp);

                heapifyBottom(arr, heapSize, 2 * index + 2, trans, gap, seconds);
            }
        }

    }

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        makeHeap(bars, trans, gap, seconds);

        for (int i = bars.length; i >= 1; i--) {
            removeRoot(bars, i, trans, gap, seconds);
            heapifyBottom(bars, i - 1, 0, trans, gap, seconds);
        }


    }

}

