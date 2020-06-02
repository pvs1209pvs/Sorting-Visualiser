package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;


import java.util.List;

public class QuickSort implements Sorter {

    private void quickSort(Bar[] arr, int l, int h, List<Animation> trans, int gap, double seconds) {
        if (l < h) {
            int broken = partition(arr, l, h, trans, gap, seconds);
            quickSort(arr, l, broken - 1, trans, gap, seconds);
            quickSort(arr, broken + 1, h, trans, gap, seconds);
        }

    }

    private int partition(Bar[] arr, int l, int h, List<Animation> trans, int gap, double seconds) {

        TranslateTransition a, b;
        int barShift = 0;

        Bar pivot = arr[h];

        int low = l - 1;
        for (int i = l; i < h; i++) {

            if (arr[i].compareTo(pivot) <= 0) {
                low++;

                barShift = Math.abs(low - i);

                if (barShift != 0) {
                    a = new TranslateTransition(Duration.seconds(seconds), arr[i]);
                    a.setByX(-1 * barShift * gap);

                    b = new TranslateTransition(Duration.seconds(seconds), arr[low]);
                    b.setByX(barShift * gap);

                    trans.add(a);
                    trans.add(b);
                }

                Bar swap = arr[low];
                arr[low] = arr[i];
                arr[i] = swap;
            }
        }

        barShift = Math.abs(low + 1 - h);

        if (barShift != 0) {

            a = new TranslateTransition(Duration.seconds(seconds), arr[low + 1]);
            a.setByX(barShift * gap);

            b = new TranslateTransition(Duration.seconds(seconds), arr[h]);
            b.setByX(-1 * barShift * gap);

            trans.add(a);
            trans.add(b);

        }

        Bar swap = arr[low + 1];
        arr[low + 1] = arr[h];
        arr[h] = swap;

        return low + 1;

    }

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {
        quickSort(bars, 0, bars.length - 1, trans, gap, seconds);
    }
}
