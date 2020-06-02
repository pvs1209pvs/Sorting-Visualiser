import sortingalgorithms.*;
import visuals.Bar;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    private Test() {

        for (int i = 0; i < 10; i++) {

            Random randomGen = new Random(i);

            int n = Math.abs(randomGen.nextInt()) % 100 + 1;
            Bar[] array = new Bar[n];

            for (int j = 0; j < array.length; j++) {
                array[j] = new Bar(0, 0, 15, Math.abs(randomGen.nextInt() % 50 + 1));
            }

            Sorter sorter = new QuickSort();
            sorter.sort(array, new ArrayList<>(),0,0);

            if (!isSorted(array)) {
                System.out.println("Fails at seed " + i);
                System.exit(0);
            }
        }

        System.out.println("Perfect Run!");

    }

    private boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i] > (array[i + 1])) {
                return false;
            }
        }
        return true;
    }

    private <T extends Comparable<T>> boolean isSorted(T[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Test();
    }
}
