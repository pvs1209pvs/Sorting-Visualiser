package sortingalgorithms;

import visuals.Bar;

import java.util.ArrayList;
import java.util.List;

public class BucketSort implements Sorter {

    private final Bucket[] list;

    static class Bucket {
        List<Bar> bucket;

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
        int divider = (int) Math.ceil((findMax(bars) + 1) / (double) list.length);

        for (Bar k : bars) {
            list[(int) Math.floor(k.getHeight() / (double) divider)].bucket.add(k);
        }

        for (Bucket value : list) {
            innerSort(value.bucket);
        }

        int index = 0;
        for (Bucket bucket : list) {
            if (!bucket.bucket.isEmpty()) {
                for (int j = 0; j < bucket.bucket.size(); j++) {
                    bars[index] = bucket.bucket.get(j);
                    index++;
                }
            }
        }
    }

    private int findMax(Bar[] arr) {
        int max = (int) arr[0].getHeight();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getHeight() > max) {
                max = (int) arr[i].getHeight();
            }
        }
        return max;
    }

    private void innerSort(List<Bar> array) {
        for (int i = 1; i < array.size(); i++) {
            Bar temp = array.get(i);
            int j = i - 1;
            while (j >= 0 && array.get(j).compareTo(temp) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }
            array.set(j + 1, temp);
        }
    }
}
