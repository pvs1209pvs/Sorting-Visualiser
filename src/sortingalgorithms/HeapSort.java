
package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.Arrays;
import java.util.List;

public class HeapSort implements Sorter {

    HeapSort() {

        int[] arr = new int[]{1, 9, 8, 9,2,1, 8, 3, 4,};

        sort(arr);
        System.out.println(Arrays.toString(arr));


    }

    private boolean isHeap(int[] heap) {
        for (int i = 0; i < heap.length / 2; i++) {

            if (2 * i + 1 < heap.length) {
                if (heap[i] < heap[2 * i + 1]) {
                    return false;
                }
            }

            if (2 * i + 2 < heap.length) {
                if (heap[i] < heap[2 * i + 2]) {
                    return false;
                }
            }

        }

        return true;
    }

    public void makeHeap(int[] arr) {

        int[] heap = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            addToHeap(heap, i, arr[i]);
        }

        System.arraycopy(heap, 0, arr, 0, heap.length);

    }

    private void addToHeap(int[] heap, int size, int value) {
        heap[size] = value;
        heapifyUp(heap, size);
    }

    private void removeRoot(int[] arr, int heapSize) {
        int temp = arr[0];
        arr[0] = arr[heapSize - 1];
        arr[heapSize - 1] = temp;
    }

    private void heapifyUp(int[] arr, int parentIndex) {

        int leftChild = 2 * parentIndex + 1 < arr.length ? arr[2 * parentIndex + 1] : Integer.MIN_VALUE;
        int rightChild = 2 * parentIndex + 2 < arr.length ? arr[2 * parentIndex + 2] : Integer.MIN_VALUE;
        int maxChild = Math.max(leftChild, rightChild);

        if (maxChild > arr[parentIndex]) {

            int temp = arr[parentIndex];

            if (leftChild > rightChild) {
                arr[parentIndex] = leftChild;
                arr[2 * parentIndex + 1] = temp;

            }
            if (rightChild > leftChild) {
                arr[parentIndex] = rightChild;
                arr[2 * parentIndex + 2] = temp;
            }

        }

        if (parentIndex != 0) {
            heapifyUp(arr, (parentIndex - 1) / 2);
        }

    }

    private void sort(int[] arr) {

        makeHeap(arr);

        for (int i = arr.length; i >= 1; i--) {
            removeRoot(arr, i);
            heapifyBottom(arr, i - 1, 0);
        }

    }

    private void heapifyBottom(int[] arr, int heapSize, int index) {

        if (index >= heapSize) {
            return;
        }

        int left = 2 * index + 1 < heapSize ? arr[2 * index + 1] : Integer.MIN_VALUE;
        int right = 2 * index + 2 < heapSize ? arr[2 * index + 2] : Integer.MIN_VALUE;
        int max = Math.max(left, right);


        if (max > arr[index]) {

            int temp = arr[index];

            if (left >= right) {
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
        // INCOMPLETE
    }

    public static void main(String[] args) {
        new HeapSort();
    }

}

