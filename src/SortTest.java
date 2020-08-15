import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sortingalgorithms.CocktailSort;
import sortingalgorithms.CycleSort;
import sortingalgorithms.QuickSort;
import sortingalgorithms.Sorter;
import visuals.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

@RunWith(Parameterized.class)
public class SortTest {

    Integer[] unsortedArray;
    Integer[] sortedArray;

    public SortTest(Integer[] unsortedArray, Integer[] sortedArray) {
        this.unsortedArray = unsortedArray;
        this.sortedArray = sortedArray;
    }


    @org.junit.Test
    public void sorted() {

        Bar[] bars = new Bar[unsortedArray.length];
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar(0, 0, 1, unsortedArray[i]);
        }

        Sorter sortingAlgo = new CycleSort();
        sortingAlgo.sort(bars, new ArrayList<>(), 0, 0);

        Integer[] algoSorted = new Integer[bars.length];
        for (int i = 0; i < algoSorted.length; i++) {
            algoSorted[i] = (int) bars[i].getHeight();
        }

        assertArrayEquals(sortedArray, algoSorted);

    }

    @Parameterized.Parameters
    public static Collection parameter() {

        Object[][] obj = new Object[10][2];

        for (int i = 0; i < 10; i++) {

            Integer[] unsorted = getRandomArray();
            Integer[] sorted = Arrays.copyOf(unsorted, unsorted.length);
            Arrays.sort(sorted);

            obj[i][0] = unsorted;
            obj[i][1] = sorted;
        }

        return Arrays.asList(obj);

    }

    private static Integer[] getRandomArray() {

        Integer[] array = new Integer[(int) (Math.random() * 100 + 1)];

        Random r = new Random();
        r.ints();

        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt();
        }

        return array;

    }

}