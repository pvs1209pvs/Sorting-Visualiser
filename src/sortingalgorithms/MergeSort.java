package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 class MergeSort implements Sorter {

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {
        sort(bars, 0, bars.length - 1, trans, gap, seconds);
    }

    private void sort(Bar[] arr, int start, int end, List<Animation> trans, int gap, double seconds) {

        if (start != end) {

            sort(arr, start, (start + end) / 2, trans, gap, seconds);
            sort(arr, (start + end) / 2 + 1, end, trans, gap, seconds);

            merge(arr, start, (start + end) / 2, (start + end) / 2 + 1, end, trans, gap, seconds);

        }

    }

    private void merge(Bar[] arr, int a, int b, int x, int y, List<Animation> trans, int gap, double seconds) {

        List<Bar> sorte = new ArrayList<>((b - a + 1) + (y - x + 1));
        int size = 0;

        List<Bar> before = new ArrayList<>(Arrays.asList(arr));

        int l = a;
        int r = x;

        while (l <= b && r <= y) {

            if (arr[r].compareTo(arr[l]) <= 0) {
                sorte.add(arr[r]);
                size++;
                r++;
            }
            else {
                sorte.add(arr[l++]);
            }

        }

        for (int i = l; i <= b; ++i) {
            sorte.add(arr[i]);
            size++;
        }

        for (int i = r; i <= y; ++i) {
            sorte.add(arr[i]);
        }

        size = 0;

        for (int i = a; i <= y; ++i) {
            arr[i] = sorte.get(size++);
        }

        List<Bar> after = new ArrayList<>(Arrays.asList(arr));

        anim(before, after, trans, gap, seconds);


    }

    private void anim(List<Bar> before, List<Bar> after, List<Animation> trans, int gap, double seconds) {

        List<Bar> barsBefore = new ArrayList<>(before);
        List<Bar> barsAfter = new ArrayList<>(after);
        List<Integer[]> swaps = new ArrayList<>();

        for (int i = 0; i < before.size(); i++) {

            Double probe = barsBefore.get(i).getHeight();

            for (int j = 0; j < barsAfter.size(); j++) {

                if (!barsAfter.get(j).isVisited && (probe.compareTo(barsAfter.get(j).getHeight()) == 0)) {

                    if (i != j) {
                        swaps.add(new Integer[]{i, j});
                    }

                    barsAfter.get(j).isVisited = true;
                    break;

                }
            }
        }

        for (int i = 0; i < swaps.size(); i++) {
            TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), barsBefore.get(swaps.get(i)[0]));
            a.setByX(gap * (swaps.get(i)[1] - swaps.get(i)[0]));
            trans.add(a);
        }

        for (Bar b : barsAfter) {
            b.isVisited = false;
        }

    }


}
