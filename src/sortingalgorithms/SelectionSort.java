package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

public class SelectionSort<T extends Comparable<T>> implements ComparativeSorter<T> {

    @Override
    public void sort(T[] array) {
        int y = array.length;

        for (int i = 0; i <y-1 ; i++) {
            int miniIndex = i;
            for (int j = i+1; j <y ; j++) {
                if(array[j].compareTo(array[miniIndex]) < 0){
                    miniIndex = j;
                }
            }

            T swap = array[miniIndex];
            array[miniIndex] = array[i];
            array[i] = swap;


        }




    }

    public void sort(Bar[] array, List<Animation> trans, int gap, double seconds) {

        int y = array.length;

        for (int i = 0; i <y-1 ; i++) {
            int miniIndex = i;
            for (int j = i; j <y ; j++) {
                if(array[j].compareTo(array[i]) > 0){
                    miniIndex = j;


                }
            }

            Bar swap = array[miniIndex];
            array[miniIndex] = array[i];
            array[i] = swap;

        }


    }


}





