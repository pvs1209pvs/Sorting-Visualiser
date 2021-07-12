
package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

public class CycleSort implements Sorter {

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        Bar item; //to hold the items that are being worked on
        int cyclestart = 0;
        int pos = 0; //to find the correct pos where to place the items

        for (int i = 0; i < bars.length; i++) {
            item = bars[i];
            cyclestart = i;
            pos = cyclestart;

            for (int j = (cyclestart + 1); j < bars.length; j++) {
                if (bars[j].compareTo(item) < 0) {
                    pos++;
                }
            }

            if (pos == cyclestart) {
                continue;
            }

            // Handle Duplicate
            while (bars[pos].compareTo(item) == 0) {
                pos++;
            }

            TranslateTransition a, b;

            //Swap
            if (pos != cyclestart) {
                Bar temp = item;
                item = bars[pos];
                bars[pos] = temp;

                a = new TranslateTransition(Duration.seconds(seconds), item);
                a.setByX(-1 * gap * Math.abs(i - pos));

                b = new TranslateTransition(Duration.seconds(seconds), bars[pos]);
                b.setByX(gap * Math.abs(i - pos));

                trans.add(a);
                trans.add(b);
            }

            while (pos != cyclestart) {
                pos = cyclestart;

                for (int j = (cyclestart + 1); j < bars.length; j++) {
                    if (bars[j].compareTo(item) < 0) {
                        pos++;
                    }
                }

                while (bars[pos].compareTo(item) == 0) {
                    pos++;
                }

                //swap
                if (item.compareTo(bars[pos]) != 0) {
                    Bar temp1 = item;
                    item = bars[pos];
                    bars[pos] = temp1;

                    a = new TranslateTransition(Duration.seconds(seconds), item);
                    a.setByX(-1 * gap * Math.abs(i - pos));

                    b = new TranslateTransition(Duration.seconds(seconds), bars[pos]);
                    b.setByX(gap * Math.abs(i - pos));

                    trans.add(a);
                    trans.add(b);
                }
            }
        }

    }
}
