package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

public class SelectionSort implements Sorter {

    @Override
    public void sort(Bar[] array, List<Animation> trans, int gap, double seconds) {

        for (int i = 0; i <array.length-1 ; i++) {
            int miniIndex = i;
            for (int j = i; j <array.length ; j++) {
                if (array[j].compareTo(array[i]) > 0) {
                    miniIndex = j;
                }
            }

            TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), array[i]);
            a.setByX((miniIndex-i) * gap);

            TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), array[miniIndex]);
            b.setByX((i-miniIndex)* gap);

            trans.add(a);
            trans.add(b);

            Bar swap = array[miniIndex];
            array[miniIndex] = array[i];
            array[i] = swap;

        }

    }

}





