package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.*;

import static sortingalgorithms.CountingSort.*;

public class RadixSort implements Sorter {

    RadixSort() {
    }

    public void sort(int[] array, int digitLen) {

        for (int i = 0; i < digitLen; i++) {
            sortIteration(array, i);
        }

    }

    private Map<Integer, List<Integer>> getDigitGroup(int[] array) {

        Map<Integer, List<Integer>> digitGroup = new HashMap<>();

        for (int number : array) {
            int digitLen = getDigitCount(number);
            digitGroup.putIfAbsent(digitLen, new ArrayList<>());
            digitGroup.get(digitLen).add(number);
        }

        return digitGroup;

    }

    private void sortIteration(int[] array, int iteration) {

        int[] countingArray = getDigitArray(array, iteration);
        rightSummation(countingArray);

        int[] temp = new int[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            temp[--countingArray[getDigit(array[i], iteration)]] = array[i];
        }

        System.arraycopy(temp, 0, array, 0, temp.length);

    }

    private int[] getDigitArray(int[] array, int iteration) {

        int[] digitArray = new int[10];
        Arrays.fill(digitArray, 0);

        for (int integer : array) {
            ++digitArray[getDigit(integer, iteration)];
        }

        return digitArray;

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

    private int[] sortDigitWise(int[] array) {

       List<Integer> sorted = new LinkedList<>();

        for (Map.Entry<Integer, List<Integer>> pair : getDigitGroup(array).entrySet()) {

            int[] digitLenWiseSorted = pair.getValue().stream().mapToInt(value->value).toArray();
            sort(digitLenWiseSorted, pair.getKey());

            Arrays.stream(digitLenWiseSorted).forEach(sorted::add);

        }

        return sorted.stream().mapToInt(value->value).toArray();

    }

    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        int[] barHeight = new int[bars.length];
        for (int i = 0; i < barHeight.length; i++) {
            barHeight[i] = (int) bars[i].getHeight();
        }

        int[] sorted = sortDigitWise(barHeight);

        for (int i = 0; i < barHeight.length; i++) {
            try {

                bars[i].setHeight(sorted[i]);
            } catch (NullPointerException e) {
                System.out.println("sorted");
                for (int j = 0; j < sorted.length; j++) {
                    System.out.print(j+":"+sorted[j] + "  ");
                }
                System.out.println("\nbars");
                for (int j = 0; j < bars.length; j++) {
                    System.out.print(j+":"+bars[j] + "  ");
                }
                System.out.println();
            }
        }


    }

    public static void main(String[] args) {
        new RadixSort();
    }
}

