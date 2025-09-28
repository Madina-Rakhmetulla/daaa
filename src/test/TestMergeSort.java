package test;

import alg.MergeSort;

import java.util.Arrays;
import java.util.Random;

public class TestMergeSort {
    public static void main(String[] args) {
        testBasic();
        testSorted();
        testReverse();
        testRandom();
        testDuplicates();
        System.out.println("All MergeSort tests passed! ✓");
    }

    static void testBasic() {
        int[] arr = {5, 2, 8, 1, 9};
        int[] expected = {1, 2, 5, 8, 9};
        MergeSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Basic test failed";
    }

    static void testSorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Sorted test failed";
    }

    static void testReverse() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Reverse test failed";
    }

    static void testRandom() {
        Random rand = new Random(42);
        for (int i = 0; i < 10; i++) {
            int[] arr = new int[100];
            for (int j = 0; j < 100; j++) arr[j] = rand.nextInt(1000);
            int[] expected = arr.clone();
            Arrays.sort(expected);
            MergeSort.sort(arr);
            assert Arrays.equals(arr, expected) : "Random test " + i + " failed";
        }
    }

    static void testDuplicates() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        MergeSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Duplicates test failed";
    }
}