package alg;

import java.util.Arrays;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        Metrics.reset();
        int[] copy = arr.clone();
        int result = select(copy, 0, copy.length-1, k, 0);
        Metrics.print("DeterministicSelect");
        return result;
    }

    static int select(int[] arr, int left, int right, int k, int depth) {
        if (depth > Metrics.maxDepth) Metrics.maxDepth = depth;
        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right);
        int pivotIndex = partition(arr, left, right, pivot);

        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return select(arr, left, pivotIndex-1, k, depth+1);
        else return select(arr, pivotIndex+1, right, k, depth+1);
    }

    static int medianOfMedians(int[] arr, int left, int right) {
        int n = right - left + 1;

        if (n <= 5) {
            Arrays.sort(arr, left, right+1);
            Metrics.allocations++; // учли сортировку
            return arr[left + n/2];
        }

        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];
        Metrics.allocations++; // массив медиан

        for (int i = 0; i < numGroups; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(groupLeft + 4, right);
            Arrays.sort(arr, groupLeft, groupRight+1);
            medians[i] = arr[groupLeft + (groupRight - groupLeft)/2];
        }

        return select(medians, 0, numGroups-1, numGroups/2, 0);
    }

    static int partition(int[] arr, int left, int right, int pivotValue) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            Metrics.comparisons++;
            if (arr[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }

        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[right];
        arr[right] = temp;

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            Metrics.comparisons++;
            if (arr[i] < pivotValue) {
                temp = arr[i];
                arr[i] = arr[storeIndex];
                arr[storeIndex] = temp;
                storeIndex++;
            }
        }

        temp = arr[storeIndex];
        arr[storeIndex] = arr[right];
        arr[right] = temp;

        return storeIndex;
    }
}
