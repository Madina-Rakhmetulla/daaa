package test;

import alg.DeterministicSelect;

import java.util.Arrays;
import java.util.Random;

public class TestDeterministicSelect {
    public static void main(String[] args) {
        testBasic();
        testRandom();
        testCompareWithSort();
        testDuplicates();
        System.out.println("All DeterministicSelect tests passed! ✓");
    }

    static void testBasic() {
        int[] arr = {5, 2, 8, 1, 9};
        assert DeterministicSelect.select(arr, 0) == 1 : "k=0 failed";
        assert DeterministicSelect.select(arr, 2) == 5 : "k=2 failed";
        assert DeterministicSelect.select(arr, 4) == 9 : "k=4 failed";
    }

    static void testCompareWithSort() {
        Random rand = new Random(42);
        for (int trial = 0; trial < 100; trial++) {
            int n = 50 + rand.nextInt(50);
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000);

            int k = rand.nextInt(n);
            int result = DeterministicSelect.select(arr, k);

            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            assert result == sorted[k] :
                    String.format("Select failed: k=%d, got %d, expected %d", k, result, sorted[k]);
        }
    }

    static void testRandom() {
        Random rand = new Random(42);
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) arr[i] = rand.nextInt(1000);

        for (int k = 0; k < 100; k++) {
            int result = DeterministicSelect.select(arr.clone(), k);
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            assert result == sorted[k] : "k=" + k + " failed";
        }
    }

    static void testDuplicates() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < arr.length; k++) {
            int result = DeterministicSelect.select(arr.clone(), k);
            assert result == sorted[k] : "k=" + k + " with duplicates failed";
        }
    }
}
