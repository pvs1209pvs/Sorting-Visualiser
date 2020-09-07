
package sortingalgorithms;

import javafx.animation.Animation;
import visuals.Bar;

import java.util.List;

 class CycleSort implements Sorter {
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

        System.out.println("unsorted");
        for (int i = 0; i < bars.length; i++) {
            System.out.print(bars[i].string()+", ");
        }
        System.out.println();

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

            while (bars[pos].compareTo(item) == 0) {
                pos++;
            }

            //Swap
            Bar temp = item;
            item = bars[pos];
            bars[pos] = temp;


            while (pos != cyclestart) {
                pos = cyclestart;

                for (int j = (cyclestart + 1); j < bars.length; j++) {
                    if (bars[j].compareTo(item) < 0) {
                        pos++;
                    }
                }

                if (pos == cyclestart) {
                    continue;
                }

                while (bars[pos].compareTo(item) == 0) {
                    pos++;
                }

                //swap
                temp = item;
                item = bars[pos];
                bars[pos] = temp;



            }
        }

        System.out.println("sorted");
        for (int i = 0; i < bars.length; i++) {
            System.out.print(bars[i].string()+", ");
        }
        System.out.println();

    }
}
