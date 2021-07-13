package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.*;

import static sortingalgorithms.CountingSort.*;

public class RadixSort implements Sorter {
    private double seconds;

    //Probably pass the original array as well for swapping
    public void sort(Bar[] array, int digitLen, Bar[] original) {

        for (int i = 0; i < digitLen; i++) {
            sortIteration(array, i, original);
        }

    }

    private Map<Integer, List<Bar>> getDigitGroup(Bar[] array) {

        Map<Integer, List<Bar>> digitGroup = new HashMap<>();

        for (Bar number : array) {
            int digitLen = getDigitCount((int) number.getHeight());
            digitGroup.putIfAbsent(digitLen, new ArrayList<>());
            digitGroup.get(digitLen).add(number);
        }

        return digitGroup;

    }

    //This is where animation must go
    private void sortIteration(Bar[] array, int iteration, Bar[] original) {

        int[] countingArray = getDigitArray(array, iteration);
        rightSummation(countingArray);

        Bar[] temp = new Bar[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            temp[--countingArray[getDigit((int) array[i].getHeight(), iteration)]] = array[i];
        }

        System.arraycopy(temp, 0, array, 0, temp.length);

    }

    //new
    private int getBar(Bar[] original, Bar bar) {
        int index = -1;
        for (int i = 0; i < original.length; i++) {
            if (original[i].equals(bar)) {
                index = i;
            }
        }

        return index;
    }

    private int[] getDigitArray(Bar[] array, int iteration) {

        int[] digitArray = new int[10];
        Arrays.fill(digitArray, 0);

        for (Bar bar : array) {
            ++digitArray[getDigit((int) bar.getHeight(), iteration)];
        }

        return digitArray;

    }

    //TODO: Or Probably try animation here

    /**
     * Does the final sorting.
     *
     * @param array Input array.
     * @return Sorted array.
     */
    private Bar[] sortDigitWise(Bar[] array) {

        List<Bar> sorted = new LinkedList<>();

        for (Map.Entry<Integer, List<Bar>> pair : getDigitGroup(array).entrySet()) {

            Bar[] digitLenWiseSorted = pair.getValue().toArray(new Bar[0]);
            sort(digitLenWiseSorted, pair.getKey(), array); //This does the internal sorting

            sorted.addAll(Arrays.asList(digitLenWiseSorted));

        }

        return sorted.toArray(new Bar[0]);

    }

    private int getDigit(int value, int iteration) {
        return (value / (int) Math.pow(10, iteration)) % 10;
    }

    private int getDigitCount(int number) {
        if (number < 100000) {
            if (number < 100) {
                if (number < 10) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                if (number < 1000) {
                    return 3;
                } else {
                    if (number < 10000) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        } else {
            if (number < 10000000) {
                if (number < 1000000) {
                    return 6;
                } else {
                    return 7;
                }
            } else {
                if (number < 100000000) {
                    return 8;
                } else {
                    if (number < 1000000000) {
                        return 9;
                    } else {
                        return 10;
                    }
                }
            }
        }
    }

    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        this.seconds = seconds;
        Bar[] sorted = sortDigitWise(bars);

        for (int i = 0; i < bars.length; i++) {
            ScaleTransition scaling = new ScaleTransition(Duration.seconds(seconds), bars[i]);
            scaling.setToY(sorted[i].getHeight() / bars[i].getHeight());
            trans.add(scaling);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(seconds), bars[i]);
            tt.setByY((sorted[i].getHeight() - bars[i].getHeight()) / 2);
            trans.add(tt);
        }

        System.arraycopy(sorted, 0, bars, 0, bars.length);
    }
}

