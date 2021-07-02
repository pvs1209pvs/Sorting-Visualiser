package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort implements Sorter {

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

        sorte.addAll(Arrays.asList(arr).subList(r, y + 1));

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

        for (Integer[] swap : swaps) {
            TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), barsBefore.get(swap[0]));
            a.setByX(gap * (swap[1] - swap[0]));
            trans.add(a);
        }

        for (Bar b : barsAfter) {
            b.isVisited = false;
        }

    }


}
