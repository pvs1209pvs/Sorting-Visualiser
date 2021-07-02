package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.*;
import java.util.stream.Collectors;

import static sortingalgorithms.CountingSort.*;

public class RadixSort implements Sorter {

    RadixSort() {

//        Integer[] array = new Integer[]{92, 88, 65, 12, 96, 87, 85, 59, 81};
////        sortDigitWise(array);
//        sort(array, 2);
//        System.out.println(Arrays.toString(array));
    }

    public void sort(Integer[] array, int digitLen) {

        for (int i = 0; i < digitLen; i++) {
            sortIteration(array, i);
        }

    }

    private Map<Integer, List<Integer>> getDigitGroup(Integer[] array) {

        Map<Integer, List<Integer>> digitGroup = new TreeMap<>();

        for (Integer number : array) {
            int digitLen = getDigitCount(number);

            digitGroup.putIfAbsent(digitLen, new ArrayList<>());
            digitGroup.get(digitLen).add(number);
        }

        return digitGroup;

    }

    private void sortIteration(Integer[] array, int iteration) {

        Integer[] countingArray = getDigitArray(array, iteration);
        rightSummation(countingArray);

        Integer[] temp = new Integer[array.length];
        for (int i = array.length - 1; i >= 0; --i) {
            temp[--countingArray[getDigit(array[i], iteration)]] = array[i];
        }

        System.arraycopy(temp, 0, array, 0, temp.length);

    }

    private Integer[] getDigitArray(Integer[] array, int iteration) {

        Integer[] digitArray = new Integer[10];
        Arrays.fill(digitArray, 0);

        for (Integer integer : array) {
            ++digitArray[getDigit(integer, iteration)];
        }

        return digitArray;

    }

    private int getDigit(Integer value, int iteration) {
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


    private Integer[] sortDigitWise(Integer[] array) {

       List<Integer> sorted = new LinkedList<>();

        for (Map.Entry<Integer, List<Integer>> pair : getDigitGroup(array).entrySet()) {

            Integer[] digitLenwiseSorted = pair.getValue().toArray(new Integer[0]);
            sort(digitLenwiseSorted, pair.getKey());

            sorted.addAll(Arrays.stream(digitLenwiseSorted).collect(Collectors.toList()));
        }

        return sorted.toArray(new Integer[0]);
    }

    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        Integer[] barHeight = new Integer[bars.length];
        for (int i = 0; i < barHeight.length; i++) {
            barHeight[i] = (int) bars[i].getHeight();
        }

        Integer[] sorted = sortDigitWise(barHeight);

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

