package sortingalgorithms;

public class CocktailSort<T extends Comparable<T>> implements ComparativeSorter<T>  {
    @Override
    public void sort(T[] array) {

        int sorted = 0;

        while(sorted==0){
            int begin = 0;
            int end = array.length;

            //pass towards right
            sorted = 1;

            for (int i =begin; i < array.length-1 ; ++i) {

                if(array[i].compareTo(array[i+1]) > 0){

                    T swap = array[i+1];
                    array[i+1] = array[i];
                    array[i] = swap;

                    sorted = 0;

                }

            }
            if(sorted==1) break;


            //pass towards left

            end = end -1;
            sorted = 0;


            for (int i =end-1; i >= begin ; i--) {

                if(array[i].compareTo(array[i+1]) > 0){

                    T swap = array[i+1];
                    array[i+1] = array[i];
                    array[i] = swap;

                    sorted = 0;

                }


            }

            ++begin;

        }

    }
}
