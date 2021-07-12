package tests;

import sortingalgorithms.*;
import visuals.Bar;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SortTest {

    private Bar[] randomBars;

    @org.junit.Before
    public void fillRandomBars() {
        randomBars = IntStream
                .range(0, 10)
                .mapToObj(x -> new Bar(0, 0, 1, ThreadLocalRandom.current().nextInt(0, 101)))
                .toArray(Bar[]::new);
    }

    @org.junit.Test
    public void bubbleSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.BUBBLE).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void bucketSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.BUCKET).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void cocktailSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.COCKTAIL).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void countingSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.COUNTING).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void cycleSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.CYCLE).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void heapSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.HEAP).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void insertionSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.INSERTION).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void mergeSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.MERGE).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void quickSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.QUICK).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void radixSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.RADIX).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void shellSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.SHELL).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    @org.junit.Test
    public void selectionSort_randomArray_successfully() {
        SorterFactory.getSorter(SorterFactory.ALGORITHMS.SELECTION).sort(randomBars, new ArrayList<>(), 0, 0);
        sortedArrayAssertion(randomBars);
    }

    private void sortedArrayAssertion(Bar[] sortedBars){
        for (int i = 0; i < sortedBars.length-1; i++) {
            assertTrue(sortedBars[i].compareTo(sortedBars[i+1]) <= 0);
        }
    }

}