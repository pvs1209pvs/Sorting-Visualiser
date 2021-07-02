package sortingalgorithms;

import visuals.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BucketSort implements Sorter {

    private final Bucket[] list;

    static class Bucket {
        List<Integer> bucket;

        Bucket() {
            bucket = new ArrayList<>();
        }
    }

    public BucketSort() {
        list = new Bucket[10];

        for (int i = 0; i < list.length; i++) {
            list[i] = new Bucket();
        }
    }

    @Override
    public void sort(Bar[] bars, List trans, int gap, double seconds) {
        Integer[] barsHeight = new Integer[bars.length];
        for (int i = 0; i < bars.length; i++) {
            barsHeight[i] = (int) bars[i].getHeight();
        }

        int divider = (int) Math.ceil(((Collections.max(Arrays.asList(barsHeight))) + 1) / (double) list.length);

        for (int k : barsHeight) {
            list[(int) Math.floor(k / (double) divider)].bucket.add(k);
        }

        for (Bucket value : list) {
            innerSort(value.bucket);
        }

        int index = 0;
        for (Bucket bucket : list) {
            if (!bucket.bucket.isEmpty()) {
                for (int j = 0; j < bucket.bucket.size(); j++) {
                    barsHeight[index] = bucket.bucket.get(j);
                    index++;
                }
            }
        }

        for (int i = 0; i < barsHeight.length; i++) {
            bars[i].setHeight(barsHeight[i]);
        }
    }

    public void sort(int[] array) {
        int divider = (int) Math.ceil(((Arrays.stream(array).max().getAsInt()) + 1) / (double) list.length);

        for (int k : array) {
            list[(int) Math.floor(k / (double) divider)].bucket.add(k);
        }

        for (Bucket value : list) {
            innerSort(value.bucket);
        }

        int index = 0;
        for (Bucket bucket : list) {
            if (!bucket.bucket.isEmpty()) {
                for (int j = 0; j < bucket.bucket.size(); j++) {
                    array[index] = bucket.bucket.get(j);
                    index++;
                }
            }
        }

        for (int k : array) System.out.println(k);
    }

    void innerSort(List<Integer> array) {
        for (int i = 1; i < array.size(); i++) {
            int temp = array.get(i);
            int j = i - 1;
            while (j >= 0 && array.get(j) > temp) {
                array.set(j + 1, array.get(j));
                j--;
            }
            array.set(j + 1, temp);
        }
    }
}
