package sortingalgorithms;


public class InsertionSort<T extends Comparable<T>> implements ComparativeSorter<T> {

    public T[] tempArray;

    public void sort(T[] array) {

        for (int i = 1; i < array.length; i++) {
            T temp = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(temp) > 0) {
                array[j + 1] = array[j];
                j--;

            }

            tempArray = array;
            array[j + 1] = temp;

        }

    }

}
