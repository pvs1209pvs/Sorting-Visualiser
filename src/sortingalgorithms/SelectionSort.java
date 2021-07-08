package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
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

        for (int i = 0; i < array.length; i++) {
            int minValIndex = findMin(i, array.length, array)[1];

            Bar temp = array[i];
            a = new TranslateTransition(Duration.seconds(seconds), array[i]);
            a.setByX(Math.abs(minValIndex - i) * gap);

            b = new TranslateTransition(Duration.seconds(seconds), array[minValIndex]);
            b.setByX(-1 * Math.abs(minValIndex - i) * gap);
            array[i] = array[minValIndex];
            array[minValIndex] = temp;

            trans.add(a);
            trans.add(b);
        }
    }
}

