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

                    TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), bars[i]);
                    a.setByX(gap);

                    Bar temp = bars[i];

                    TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), bars[i + 1]);
                    b.setByX(-1 * gap);

                    bars[i] = bars[i + 1];
                    bars[i + 1] = temp;

                    trans.add(a);
                    trans.add(b);

                    isSorted = false;
                }
            }
        }
    }
}
