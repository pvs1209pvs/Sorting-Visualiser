import sortingalgorithms.*;

import java.util.Random;

public class Test {

    private Test() {

        for (int i = 0; i < 10; i++) {

            Random randomGen = new Random(i);

            int n = Math.abs(randomGen.nextInt()) % 100 + 1;
            Integer[] array = new Integer[n];
            
            for (int j = 0; j < array.length; j++) {
                array[j] = Math.abs(randomGen.nextInt() % 50 + 1);
            }

//            CycleSort<Integer> s = new CycleSort<>();
//            s.sort(array);

            if (!isSorted(array)) {
                System.out.println("Fails at seed " + i);
            }
        }

//
//        Integer a = 15;
//        Integer b = 20;
//        System.out.println(a.compareTo(b)<0);
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
