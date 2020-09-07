
package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 class HeapSort implements Sorter {

    private List<Bar> minHeap;
    private int heapSize;

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        minHeap = new ArrayList<>();
        for (int i = 0; i < bars.length; i++) {
            minHeap.add(null);
        }
        heapSize = 0;

        minHeap.set(0, bars[0]);
        heapSize++;

        for (int i = 1; i < bars.length; ++i) {
            add_to_heap(bars[i], bars, trans, gap, seconds);
        }

        for (int l = 0; l < bars.length; ++l) {
            bars[l] = remove_from_heap(0,bars, trans, gap, seconds);
        }
    }

    private void add_to_heap(Bar v, Bar bars[], List<Animation> trans, int gap, double seconds) {
        minHeap.set(heapSize, v);
        insertion_heapify(heapSize, bars, trans, gap, seconds);
        heapSize++;
    }

    private void insertion_heapify(int c,Bar bars[], List<Animation> trans, int gap, double seconds) {
        while ((c - 1) / 2 >= 0 &&
                minHeap.get((c - 1) / 2).compareTo(minHeap.get(c))>0) {
            swap(c, (c - 1) / 2, bars, trans, gap, seconds);
            c = (c - 1) / 2;
        }
    }

    private Bar remove_from_heap(int ele_index,Bar bars[], List<Animation> trans, int gap, double seconds) {

        Bar ele = minHeap.get(ele_index); // delete the element
        Bar last_ele = minHeap.get(--heapSize); // get the last element
        minHeap.set(ele_index, last_ele); // save the last ele at the deleted ele's place.

        if (last_ele.compareTo(minHeap.get((ele_index - 1) / 2)) < 0) {
            heapify_above(ele_index,bars, trans, gap, seconds);
        }
        else {
            heapify_below(ele_index,bars, trans, gap, seconds);
        }

        return ele;
    }

    private void heapify_below(int p,Bar bars[], List<Animation> trans, int gap, double seconds) {

        int left = p * 2 + 1;
        int right = p * 2 + 2;
        int min = p;

        if (left >= heapSize) return;

        if (minHeap.get(left).compareTo(minHeap.get(right)) <= 0) min = left;
        if (minHeap.get(right).compareTo(minHeap.get(left)) < 0) min = right;

        if (p == min) return;

        if (minHeap.get(p).compareTo(minHeap.get(min)) > 0) {
            swap(p, min, bars, trans, gap, seconds);
            heapify_below(min, bars, trans, gap, seconds);
        }

    }

    private void heapify_above(int p,Bar bars[], List<Animation> trans, int gap, double seconds) {

        if ((p - 1) / 2 < 0) {
            return;
        }
        else {
            if (minHeap.get(p).compareTo(minHeap.get((p - 1) / 2))<0) {
                swap(p, (p - 1) / 2,bars, trans, gap, seconds);
                p = (p - 1) / 2;
                heapify_above(p, bars, trans, gap, seconds);
            }
            else {
                return;
            }
        }

    }

    private void swap(int x, int y, Bar bars[], List<Animation> trans, int gap, double seconds) {

        System.out.println(Arrays.toString(minHeap.toArray(new Bar[0])));

        Bar temp = minHeap.get(x);
        minHeap.set(x, minHeap.get(y));
        minHeap.set(y, temp);

        System.out.println(Arrays.toString(minHeap.toArray(new Bar[0]))+"\n");


        TranslateTransition a, b;

        a = new TranslateTransition(Duration.seconds(seconds), minHeap.toArray(new Bar[0])[x]);
        a.setByX(1 * gap);

        b = new TranslateTransition(Duration.seconds(seconds), minHeap.toArray(new Bar[0])[y]);
        b.setByX(-1 * gap);

        trans.add(a);
        trans.add(b);
    }

}

