package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import visuals.Bar;

import java.util.List;

public class SelectionSort implements Sorter {

    private int[] findMin(int i, int j, Bar[] arr) {
        int[] min = {(int) arr[i].getHeight(), i};
        for (int k = i; k < j; k++) {
            if (arr[k].getHeight() < min[0]) {
                min[0] = (int) arr[k].getHeight();
                min[1] = k;
            }
        }
        return min;
    }

    @Override
    public void sort(Bar[] array, List<Animation> trans, int gap, double seconds) {

        TranslateTransition a, b;

        //Okay, using transition than equating set height
        for (int i = 0; i < array.length; i++) {
            int minValIndex = findMin(i, array.length, array)[1];
            Bar temp = array[i];
            array[i] = array[minValIndex];
            array[minValIndex] = temp;
        }
    }
}

//TODO: Compare using the height directly to avoid the seHeight Issue

//        Integer[] unsorted = new Integer[array.length];
//
//        for (int i = 0; i < array.length; i++) {
//            unsorted[i] = (int) array[i].getHeight();
//        }
//
//        Integer[] sorted = sort(unsorted);
//
//        for (int i = 0; i < sorted.length; i++) {
//            array[i].setHeight(sorted[i]);
//        }

//    private void sort(Integer[] unsorted) {
//        TranslateTransition a, b;
//
//        for (int i = 0; i < unsorted.length; i++) {
//            int[] minVal = findMin(i, unsorted.length, unsorted);
//            int temp = unsorted[i];
//            unsorted[i] = minVal[0];
//            unsorted[minVal[1]] = temp;
//        }
//    }


