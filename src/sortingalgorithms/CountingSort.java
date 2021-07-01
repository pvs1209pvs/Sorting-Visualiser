package sortingalgorithms;

import javafx.animation.Animation;
import org.jetbrains.annotations.NotNull;
import visuals.Bar;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountingSort implements Sorter {

    CountingSort() {
        Random random = new Random();
        Integer[] a = new Integer[1000];
//        for (int i = 0; i < a.length; i++) {
//            a[i] = ThreadLocalRandom.current().nextInt(0, 100 + 1);
//        }

       a =  Arrays.stream(new Integer[10]).map(value -> 10).toArray(Integer[]::new);

      //  System.out.println(Arrays.toString(a));
        //sort(a);

    }

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

    private void rightSummation(Integer[] countingArray) {
        for (int i = 1; i < countingArray.length; i++) {
            countingArray[i] += countingArray[i - 1];
        }
    }

    private Integer @NotNull [] getCountingArray(Integer[] array) {

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
        }

        Integer[] sortedArray = sort(barsArray);

        for (int i = 0; i < bars.length; i++) {
            bars[i].setHeight(sortedArray[i]);
        }

    }

    public static void main(String[] args) {
        new CountingSort();
    }
}
