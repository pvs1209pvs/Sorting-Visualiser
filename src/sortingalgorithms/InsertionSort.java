package sortingalgorithms;


import javafx.animation.Animation;
import visuals.Bar;

import java.util.List;

public class InsertionSort<T extends Comparable<T>> implements ComparativeSorter<T> {

     public void sort(T[] array) {

        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(temp) > 0) {
                array[j + 1] = array[j];
                j--;

            }
            array[j + 1] = temp;

        }

    }

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

    }
}
