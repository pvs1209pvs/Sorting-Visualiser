
package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

public class CycleSort implements Sorter {
//    public void sort(T[] bars) {
//
//        T item = null; //to hold the items that are being worked on
//        int cyclestart = 0;
//        int pos = 0; //to find the correct pos where to place the items
//
//        for (int i = 0; i < bars.length; i++) {
//            item = bars[i];
//            cyclestart = i;
//            pos = cyclestart;
//
//            for (int j = (cyclestart + 1); j < bars.length; j++) {
//                if (bars[j].compareTo(item) < 0) {
//                    pos++;
//                }
//            }
//
//            if (pos == cyclestart) {
//                continue;
//            }
//
//            while (bars[pos] == item) {
//                pos++;
//            }
//
//            //Swap
//            T temp = item;
//            item = bars[pos];
//            bars[pos] = temp;
//
//            while (pos != cyclestart) {
//                pos = cyclestart;
//
//                for (int j = (cyclestart + 1); j < bars.length; j++) {
//                    if (bars[j].compareTo(item) < 0) {
//                        pos++;
//                    }
//                }
//
//                while (bars[pos] == item) {
//                    pos++;
//                }
//
//                //swap
//                temp = item;
//                item = bars[pos];
//                bars[pos] = temp;
//            }
//        }
//    }

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

        Bar item = null; //to hold the items that are being worked on
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

            if (pos == cyclestart) { continue; }

            while (bars[pos] == item) { pos++; }

            //Swap
            Bar temp = item;
            item = bars[pos];
            bars[pos] = temp;

            TranslateTransition a, b;

            a = new TranslateTransition(Duration.seconds(seconds), bars[i]);
            a.setByX(-1 * gap);

            b = new TranslateTransition(Duration.seconds(seconds), bars[pos]);
            b.setByX(1 * gap);

            trans.add(a);
            trans.add(b);

            while (pos != cyclestart) {
                pos = cyclestart;

                int j=0;
                for (j = (cyclestart + 1); j < bars.length; j++) {
                    if (bars[j].compareTo(item) < 0) {
                        pos++;
                    }
                }

                while (bars[pos] == item) {
                    pos++;
                }

                //swap
                temp = item;
                item = bars[pos];
                bars[pos] = temp;

                TranslateTransition c, d;

                c = new TranslateTransition(Duration.seconds(seconds), bars[j]);
                c.setByX(-1 * gap);

                d = new TranslateTransition(Duration.seconds(seconds), bars[pos]);
                d.setByX(1 * gap);

                trans.add(c);
                trans.add(d);
            }
        }
    }
}
