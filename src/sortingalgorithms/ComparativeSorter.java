package sortingalgorithms;

public interface ComparativeSorter<T extends Comparable<T>> extends Sorter {

     void sort(T[] array);
}
