package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.*;

public class CountingSort implements Sorter {

    public Integer[] sort(Integer[] array) {

        Integer[] countingArray = getCountingArray(array);

        rightSummation(countingArray);
        rightShift(countingArray);

        Integer[] sorted = new Integer[array.length];
        for (Integer num : array) {
            int min = Arrays.stream(array).min(Integer::compareTo).get();
            sorted[countingArray[num - min]++] = num;
        }

        return sorted;

    }

    private void rightShift(Integer[] countingArray) {
        System.arraycopy(countingArray, 0, countingArray, 1, countingArray.length - 1);
        countingArray[0] = 0;
    }

    public static void rightSummation(Integer[] countingArray) {
        for (int i = 1; i < countingArray.length; i++) {
            countingArray[i] += countingArray[i - 1];
        }
    }

    private Integer[] getCountingArray(Integer[] array) {

        int min = Arrays.stream(array).min(Integer::compareTo).get();
        int max = Arrays.stream(array).max(Integer::compareTo).get();
        int offset = max - min;

        Integer[] countingArray = new Integer[offset + 1];
        Arrays.fill(countingArray, 0);

        for (int num : array) {
            countingArray[num - min]++;
        }

        return countingArray;

    }


    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        Integer[] barsArray = new Integer[bars.length];
        for (int i = 0; i < barsArray.length; i++) {
            barsArray[i] = (int) bars[i].getHeight();

            System.out.println("input array");
            System.out.println(Arrays.toString(barsArray));

            Integer[] sortedArray = sort(barsArray);

//        for (int i = 0; i < bars.length; i++) {
//            bars[i].setHeight(sortedArray[i]);
//        }

            System.out.println("sorted");
            System.out.println(Arrays.toString(bars));

        }

    }