
package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapSort implements Sorter {

    HeapSort() {

        heapify(new int[]{5, 6, 7, 1, 8, 3, 4});
    }

    public void heapify(int[] arr) {

        int heapifyStartIndex = arr.length / 2 - 1;

        for (int i = heapifyStartIndex; i >= 0; --i) {
            heapify(arr, i);
        }

        System.out.println(Arrays.toString(arr));

    }

    private void heapify(int[] arr, int parentIndex) {

        int leftChild = arr[2 * parentIndex + 1];
        int rightChild = arr[2 * parentIndex + 2];
        int minChild = Math.min(leftChild, rightChild);

        if (minChild < arr[parentIndex]) {

            int temp = arr[parentIndex];

            if (leftChild < rightChild) {
                arr[parentIndex] = leftChild;
                arr[2 * parentIndex + 1] = temp;

            }
            if (rightChild < leftChild) {
                arr[parentIndex] = rightChild;
                arr[2 * parentIndex + 2] = temp;
            }

        }

    }


    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {
        // INCOMPLETE
    }

    public static void main(String[] args) {
        new HeapSort();
    }
}

