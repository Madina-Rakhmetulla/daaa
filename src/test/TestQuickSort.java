package test;
import alg.QuickSort;
import alg.Metrics;

import java.util.Arrays;
import java.util.Random;

public class TestQuickSort {
    public static void main(String[] args) {
        testBasic();
        testSorted();
        testReverse();
        testRandom();
        testDuplicates();
        testDepth();
        System.out.println("All QuickSort tests passed! ✓");
    }

    static void testBasic() {
        int[] arr = {5, 2, 8, 1, 9};
        int[] expected = {1, 2, 5, 8, 9};
        QuickSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Basic test failed";
    }

    static void testDepth() {
        // Проверяем что глубина рекурсии ограничена
        int[] arr = new int[1000];
        Random rand = new Random(42);
        for (int i = 0; i < 1000; i++) arr[i] = rand.nextInt(10000);
        QuickSort.sort(arr);
        assert Metrics.maxDepth <= 50 : "Depth too high: " + Metrics.maxDepth;
    }

    static void testRandom() {
        Random rand = new Random(42);
        for (int i = 0; i < 10; i++) {
            int[] arr = new int[100];
            for (int j = 0; j < 100; j++) arr[j] = rand.nextInt(1000);
            int[] expected = arr.clone();
            Arrays.sort(expected);
            QuickSort.sort(arr);
            assert Arrays.equals(arr, expected) : "Random test " + i + " failed";
        }
    }

    static void testDuplicates() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        QuickSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Duplicates test failed";
    }

    static void testSorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        QuickSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Sorted test failed";
    }

    static void testReverse() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        QuickSort.sort(arr);
        assert Arrays.equals(arr, expected) : "Reverse test failed";
    }
}
