package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> implements ComparativeSorter<T> {

    public void sort(T[] array) {

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;

                    isSorted = false;
                }
            }
        }
    }


    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        boolean isSorted = false;

        while (!isSorted) {

            isSorted = true;

            for (int i = 0; i < bars.length - 1; i++) {
                if (bars[i].compareTo(bars[i + 1]) > 0) {

                    Bar temp = bars[i];
                    bars[i] = bars[i + 1];
                    bars[i + 1] = temp;


                    TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), bars[i]);
                    a.setByX(-1*gap);

                    TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), bars[i + 1]);
                    b.setByX(gap);

                    trans.add(a);
                    trans.add(b);




                    isSorted = false;
                }
            }

        }

    }


}
