package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

 class CocktailSort implements Sorter {

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {
        int sorted = 0;

        while(sorted==0){
            int begin = 0;
            int end = bars.length;

            sorted = 1;

            for (int i =begin; i < bars.length-1 ; ++i) {
                if(bars[i].compareTo(bars[i+1]) > 0){
                    Bar swap = bars[i+1];
                    bars[i+1] = bars[i];
                    bars[i] = swap;
                    sorted = 0;

                    TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), bars[i+1]);
                    a.setByX(gap);

                    TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), bars[i]);
                    b.setByX(-1*gap);

                    trans.add(a);
                    trans.add(b);

                }
            }

            if(sorted==1) break;

            end = end -1;
            sorted = 0;

            for (int i =end-1; i >= begin ; i--) {
                if(bars[i].compareTo(bars[i+1]) > 0){
                    Bar swap = bars[i+1];
                    bars[i+1] = bars[i];
                    bars[i] = swap;
                    sorted = 0;

                    TranslateTransition a = new TranslateTransition(Duration.seconds(seconds), bars[i+1]);
                    a.setByX(gap);

                    TranslateTransition b = new TranslateTransition(Duration.seconds(seconds), bars[i]);
                    b.setByX(-1*gap);

                    trans.add(a);
                    trans.add(b);
                }
            }

            ++begin;

        }
    }

}
