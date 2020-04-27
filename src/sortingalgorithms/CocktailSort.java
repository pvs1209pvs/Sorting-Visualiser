package sortingalgorithms;

public class CocktailSort<T extends Comparable<T>> implements ComparativeSorter<T>  {
    @Override
    public void sort(T[] array) {

        int sorted = 1;

        while(sorted==0){
            int begin = 0;
            int end = array.length -1;

            //pass towards right
            sorted = 0;

            for (int i =begin; i < array.length-1 ; i++) {

                if(array[i].compareTo(array[i+1]) > 0){

                    T swap = array[i+1];
                    array[i+1] = array[i];
                    array[i] = swap;

                    sorted = 1;

                }
            }
            if(sorted==0) break;


            //pass towards left

            --end;
            sorted = 0;


            for (int i =end-1; i >= begin ; --i) {

                if(array[i].compareTo(array[i+1]) > 0){

                    T swap = array[i+1];
                    array[i+1] = array[i];
                    array[i] = swap;

                    sorted = 1;

                }
            }

            ++begin;

        }
    }
}
