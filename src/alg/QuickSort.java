package alg;

import java.util.Random;

public class QuickSort {
    static Random rand = new Random();

    public static void sort(int[] arr) {
        Metrics.reset();
        sort(arr, 0, arr.length-1, 0);
        Metrics.print("QuickSort");
    }

    static void sort(int[] arr, int left, int right, int depth) {
        if (depth > Metrics.maxDepth) Metrics.maxDepth = depth;
        if (left >= right) return;

        int pivotIndex = left + rand.nextInt(right-left+1);
        int pivot = arr[pivotIndex];

        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) { i++; Metrics.comparisons++; }
            while (arr[j] > pivot) { j--; Metrics.comparisons++; }
            Metrics.comparisons += 2;
            if (i <= j) {
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                i++; j--;
            }
        }

        if (j - left < right - i) {
            sort(arr, left, j, depth+1);
            sort(arr, i, right, depth+1);
        } else {
            sort(arr, i, right, depth+1);
            sort(arr, left, j, depth+1);
        }
    }
}