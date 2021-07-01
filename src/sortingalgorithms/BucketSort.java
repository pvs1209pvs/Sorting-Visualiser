package sortingalgorithms;

import visuals.Bar;

import java.util.ArrayList;
import java.util.List;

public class BucketSort implements Sorter{

    private final Bucket[] list;

    @Override
    public void sort(Bar[] bars, List trans, int gap, double seconds) {
        // INCOMPLETE
    }

    static class Bucket {
        List<Double> bucket;
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

    public void sort(double[] array) {

        for (double v : array) {
            list[(int) v * list.length].bucket.add(v);
        }

        for (Bucket bucket : list) {
            innerSort(bucket.bucket);
        }

        int index = 0;
        for (Bucket bucket : list) {
            for (int j = 0; j < bucket.bucket.size(); j++) {
                array[index] = bucket.bucket.get(j);
                index++;
            }
        }
    }

    void innerSort(List<Double> array) {
        for (int i = 1; i < array.size(); i++) {
            double temp = array.get(i);
            int j = i - 1;
            while (j >= 0 && array.get(j) > temp) {
                array.set(j + 1, array.get(j));
                j--;
            }
            array.set(j + 1, temp);
        }

    }
}
