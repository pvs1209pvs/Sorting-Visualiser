package sortingalgorithms;

import java.util.HashMap;
import java.util.Map;

public class SorterFactory {

    public enum ALGORITHMS {
        BUBBLE,
        BUCKET,
        COCKTAIL,
        COUNTING,
        CYCLE,
        HEAP,
        INSERTION,
        MERGE,
        QUICK,
        RADIX,
        SELECTION,
        SHELL
    }

    public static Sorter getSorter(String algorithm) {
        ALGORITHMS[] algoArray = ALGORITHMS.values();
        ALGORITHMS algo = null;

        for (ALGORITHMS a : algoArray) {
            if (a.toString().equals(algorithm)) {
                algo = a;
            }
        }

        Map<ALGORITHMS, Sorter> sorters = new HashMap<>();

        sorters.put(ALGORITHMS.BUBBLE, new BubbleSort());
        sorters.put(ALGORITHMS.BUCKET, new BucketSort());
        sorters.put(ALGORITHMS.COCKTAIL, new CocktailSort());
        sorters.put(ALGORITHMS.COUNTING, new CountingSort());
        sorters.put(ALGORITHMS.CYCLE, new CycleSort());
        sorters.put(ALGORITHMS.HEAP, new HeapSort());
        sorters.put(ALGORITHMS.INSERTION, new InsertionSort());
        sorters.put(ALGORITHMS.MERGE, new MergeSort());
        sorters.put(ALGORITHMS.QUICK, new QuickSort());
        sorters.put(ALGORITHMS.RADIX, new RadixSort());
        sorters.put(ALGORITHMS.SELECTION, new SelectionSort());
        sorters.put(ALGORITHMS.SHELL, new ShellSort());

        return sorters.get(algo);

    }
}
