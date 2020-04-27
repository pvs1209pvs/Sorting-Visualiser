



package sortingalgorithms;

public class SelectionSort<T extends Comparable<T>> implements ComparativeSorter<T> {



    @Override
    public void sort(T[] array) {
        int y = array.length;

        for (int i = 0; i <y-1 ; i++) {
            int miniIndex = i;
            for (int j = i; j <y ; j++) {
                if(array[j].compareTo(array[i]) > 0){
                    miniIndex = j;
                }
            }

            T swap = array[miniIndex];
            array[miniIndex] = array[i];
            array[i] = swap;

        }


        for(int i = 0; i<y; i++){
            System.out.println((Integer) array[i]);
        }

    }


}





