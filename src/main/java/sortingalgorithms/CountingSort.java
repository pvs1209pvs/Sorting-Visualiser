package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.*;


 class CountingSort implements Sorter {

    private void rightShift(int[] countingArray) {
        System.arraycopy(countingArray, 0, countingArray, 1, countingArray.length - 1);
        countingArray[0] = 0;
    }

    public static void rightSummation(int[] countingArray) {
        for (int i = 1; i < countingArray.length; i++) {
            countingArray[i] += countingArray[i - 1];
        }
    }

    private int[] getCountingArray(Bar[] array) {

        int min = (int) Arrays.stream(array).min(Bar::compareTo).get().getHeight();
        int max = (int) Arrays.stream(array).max(Bar::compareTo).get().getHeight();
        int offset = max - min;

        int[] countingArray = new int[offset + 1];
        Arrays.fill(countingArray, 0);

        for (Bar num : array) {
            countingArray[(int) (num.getHeight() - min)]++;
        }

        return countingArray;

    }


    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        int[] countingArray = getCountingArray(bars);

        rightSummation(countingArray);
        rightShift(countingArray);

        Bar[] sorted = new Bar[bars.length];

        for (Bar num : bars) {
            int min = (int) Arrays.stream(bars).min(Bar::compareTo).get().getHeight();
            sorted[countingArray[(int) (num.getHeight() - min)]++] = num;
        }

        System.arraycopy(sorted, 0, bars, 0, bars.length);

        for (int i = 0; i < bars.length; i++) {
            ScaleTransition scaling = new ScaleTransition(Duration.seconds(seconds), bars[i]);
            scaling.setToY(sorted[i].getHeight() / bars[i].getHeight());
            trans.add(scaling);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(seconds), bars[i]);
            tt.setByY((sorted[i].getHeight() - bars[i].getHeight()) / 2);
            trans.add(tt);
        }


    }

}