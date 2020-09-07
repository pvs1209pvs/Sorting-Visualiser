package sortingalgorithms;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import visuals.Bar;

import java.util.List;

 class SelectionSort implements Sorter {

    @Override
    public void sort(Bar[] array, List<Animation> trans, int gap, double seconds) {

        TranslateTransition a, b;
        int barShift = 0;

        int y = array.length;

        for (int i = 0; i <y-1 ; i++) {
            int miniIndex = i;
            for (int j = i; j <y ; j++) {
                if(array[j].compareTo(array[i]) > 0){
                    miniIndex = j;
                }
            }

            barShift = Math.abs(miniIndex-i);

            a = new TranslateTransition(Duration.seconds(seconds), array[i]);
            a.setByX(barShift * gap);

            b = new TranslateTransition(Duration.seconds(seconds), array[miniIndex]);
            b.setByX(-1*barShift * gap);

            trans.add(a);
            trans.add(b);

            Bar swap = array[miniIndex];
            array[miniIndex] = array[i];
            array[i] = swap;

        }

    }

}





