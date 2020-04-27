package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

public class ShellSort<T extends Comparable<T>> implements ComparativeSorter<T> {

    public void sort(T[] array) {

        int gap = array.length / 2;

        while (gap >= 1) {
            gapShift(array, gap);
            gap = gap / 2;
        }

    }

    private void gapShift(T[] array, int gap) {

        for (int i = gap; i < array.length; ++i) {
            for (int j = i; j >= gap; j = j - gap) {
                if (array[j].compareTo(array[j - gap]) < 0) {
                    swap(array, j, j - gap);
                }
            }
        }

    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public void sort(Bar[] bars, List<Animation> trans, int gaps, double seconds) {

        int gap = bars.length / 2;
        while (gap >= 1) {
            gapShiftBar(bars, gap, gaps, trans, seconds);
            gap = gap / 2;
        }
    }

    private void gapShiftBar(Bar[] array, int gap, int gaps, List<Animation> trans, double s) {

        for (int i = gap; i < array.length; ++i) {
            for (int j = i; j >= gap; j = j - gap) {
                if (array[j].compareTo(array[j - gap]) < 0) {
                    swapbars(array, j, j - gap, gaps, s, trans);
                }
            }
        }

    }

    private void swapbars(Bar[] array, int i, int j, int gaps, double seconds, List<Animation> trans) {
        Bar temp = array[i];
        System.out.println("Before " + array[i].getX() + " " + array[j].getX());
        array[i] = array[j];
        array[j] = temp;
        System.out.println("After " + array[i].getX() + " " + array[j].getX());

        TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), array[i]);
        a.setByX(array[j].getX());

        TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), array[j]);
        b.setByX(array[i].getX());

        trans.add(a);
        trans.add(b);
    }
}
