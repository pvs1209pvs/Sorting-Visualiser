package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.List;

public class CountingSort implements NonComparativeSorter<Integer>{

    public void sort(Integer[] array) {

        int[] freqArray = new int[(maxValue(array) - minValue(array)) + 1];

        countFreq(freqArray, array);
        cumulativeFreqCalc(freqArray);
        rigthShift(freqArray);

        int[] sorted = countSort(array, freqArray);

        for (int i = 0; i < array.length; i++) {
            array[i] = sorted[i];
        }

    }

    private  int[] countSort(Integer[] array, int[] freqArray) {

        int[] sorted = new int[array.length];

        for (int value : array) {
            sorted[freqArray[value - minValue(array)]++] = value;
        }

        return sorted;
    }

    private  void countFreq(int[] freqArray, Integer[] array) {
        for (int value : array) {
            freqArray[value - minValue(array)]++;
        }
    }

    private  void cumulativeFreqCalc(int[] freqArray) {
        for (int i = 1; i < freqArray.length; i++) {
            freqArray[i] += freqArray[i - 1];
        }
    }

    private  void rigthShift(int[] freqArray) {

        for (int i = freqArray.length - 1; i >= 1; --i) {
            freqArray[i] = freqArray[i - 1];
        }

        freqArray[0] = 0;

    }

    private Integer minValue(Integer[] array) {

        Integer min = array[0];

        for (Integer value : array) {
            if (value < min) {
                min = value;
            }
        }

        return min;

    }

    private  Integer maxValue(Integer[] array) {

        Integer max = array[0];

        for (Integer value : array) {
            if (value > max) {
                max = value;
            }
        }

        return max;

    }

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

    }
}
