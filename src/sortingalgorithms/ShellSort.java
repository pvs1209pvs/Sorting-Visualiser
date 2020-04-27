package sortingalgorithms;

public class ShellSort<T extends  Comparable<T>> implements ComparativeSorter<T>{

     public void sort(T[] array){

         int gap = array.length/2;

         while(gap>=1) {
             gapShift(array, gap);
             gap = gap/2;
         }

    }

    private void gapShift(T[] array, int gap) {

        for (int i = gap; i < array.length; ++i) {
            for (int j = i; j >=gap ; j=j-gap) {
                if (array[j].compareTo(array[j - gap])<0) {
                    swap(array, j, j - gap);
                }
            }
        }

    }

    private void swap(T[] array, int i, int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
