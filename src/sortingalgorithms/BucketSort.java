package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {
        int divider = (int) Math.ceil((findMax(bars) + 1) / (double) list.length);
        Bar[] sortedBars = new Bar[bars.length];

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
                    sortedBars[index] = bucket.bucket.get(j);
                    index++;
                }
            }
        }

        for (int i = 0; i < bars.length; i++) {
            ScaleTransition scaling = new ScaleTransition(Duration.seconds(seconds), bars[i]);
            scaling.setToY(sortedBars[i].getHeight() / bars[i].getHeight());
            trans.add(scaling);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(seconds), bars[i]);
            tt.setByY((sortedBars[i].getHeight() - bars[i].getHeight()) / 2);
            trans.add(tt);
        }
    }

    private int findMax(Bar[] arr) {
        return (int) Arrays.stream(arr).max(Bar::compareTo).get().getHeight();
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
