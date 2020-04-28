package sortingalgorithms;


import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
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

        for (int i = 1; i < bars.length; i++) {
            Bar temp = bars[i];
            int j = i - 1;

            while (j >= 0 && bars[j].compareTo(temp) > 0) {
                bars[j + 1] = bars[j];
                TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), bars[j + 1]);
                a.setByX(gap);
                trans.add(a);
                j--;
            }

            bars[j + 1] = temp;
            TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), temp);
            b.setByX(-1 * gap * Math.abs(i - (j + 1)));
            trans.add(b);
        }
    }
}
