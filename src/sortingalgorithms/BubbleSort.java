package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

public class BubbleSort implements Sorter {

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        boolean isSorted = false;

        while (!isSorted) {

            isSorted = true;

            for (int i = 0; i < bars.length - 1; i++) {
                if (bars[i].compareTo(bars[i + 1]) > 0) {

                    Bar temp = bars[i];
                    bars[i] = bars[i + 1];
                    bars[i + 1] = temp;

                    TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), bars[i]);
                    a.setByX(-1 * gap);

                    TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), bars[i + 1]);
                    b.setByX(gap);

                    trans.add(a);
                    trans.add(b);

                    isSorted = false;
                }
            }
        }
    }
}
