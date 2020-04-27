package sortingalgorithms;

public class QuickSort<T extends Comparable<T>> implements ComparativeSorter<T>  {
    @Override
    public void sort(T[] array) {
        QuickSrt(array,0, array.length-1);
    }

    //Breaking and partition nested calls
    private void QuickSrt(T[] arr, int l, int h){
        if(l<h){
            int broken = brokenHearts(arr,l,h);
            QuickSrt(arr, l, broken-1);
            QuickSrt(arr, broken+1, h);
        }

    }

    //Breaking given array using high and low of that array
    private int brokenHearts(T[] arr, int l, int h) {

        T pivot = arr[h];

        int low = l-1;
        for (int i = l; i <h ; i++) {

            if(arr[i].compareTo(pivot)<=0){
                low++;
                T swap = arr[low];
                arr[low] = arr[i];
                arr[i] = swap;
            }
        }
        T swap = arr[low+1];
        arr[low+1] = arr[h];
        arr[h] = swap;

        return low+1;

    }
}
