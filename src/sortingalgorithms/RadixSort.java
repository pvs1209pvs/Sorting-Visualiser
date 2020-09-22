package sortingalgorithms;
import javafx.animation.Animation;
import visuals.Bar;

import java.util.ArrayList;
import java.util.List;

public class RadixSort implements NonComparativeSorter<Integer> {

    ArrayList<ArrayList<String>> baguette;
    String[] puppet;

    @Override
    public void sort(Integer[] array) {

        baguette= new ArrayList<ArrayList<String>>();
        for (int i = 0; i < 10; i++) {
            baguette.add(new ArrayList<String>());
        }

        puppet = new String[array.length];

        //get largest
        int large = 0;
        for (int i = 0; i <array.length ; i++) {
            if(array[i]>large){
                large = array[i];
            }
        }

        //length  of the largest
        int lenLarge  = Integer.toString(large).length();

        //padding
        for (int i = 0; i <array.length ; i++) {
            String s = Integer.toString(array[i]);
            int add = lenLarge-s.length();
            String temp = "";
            for (int j = 0; j < add; j++) {
                temp = temp + "0";
            }
            puppet[i] = temp + s;
        }

        //adding to buckets
        for (int i = lenLarge-1; i >=0 ; i--) {
            for (int j = 0; j <puppet.length ; j++) {
                int k = Integer.parseInt(puppet[j].charAt(i)+"");
                baguette.get(k).add(puppet[j]);
            }
            addBack();
        }

        //Kind of returining
        for (int i = 0; i < puppet.length; i++) {
            array[i] = Integer.parseInt(puppet[i]);
        }

    }

    private void addBack() {
        int counter = 0;
        for (int i = 0; i < baguette.size(); i++) {
            if(baguette.get(i).isEmpty()) continue;
            for (int j = 0; j < baguette.get(i).size() ; j++) {
                puppet[counter] = baguette.get(i).get(j);
                counter++;
            }
        }

        for (int i = 0; i < baguette.size() ; i++) {
            baguette.get(i).clear();
        }
    }

    @Override
    public void sort(Bar[] bars, List<Animation> trans, int gap, double seconds) {

    }
}

