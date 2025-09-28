package alg;

public class MergeSort {
    static int[] buffer;

    public static void sort(int[] arr) {
        Metrics.reset();
        buffer = new int[arr.length];
        sort(arr, 0, arr.length-1, 0);
        Metrics.print("MergeSort");
    }

    static void sort(int[] arr, int left, int right, int depth) {
        if (depth > Metrics.maxDepth) Metrics.maxDepth = depth;

        if (left >= right) return;

        if (right - left < 16) {
            for (int i = left+1; i <= right; i++) {
                int key = arr[i], j = i-1;
                while (j >= left && arr[j] > key) {
                    Metrics.comparisons++;
                    arr[j+1] = arr[j--];
                }
                Metrics.comparisons++;
                arr[j+1] = key;
            }
            return;
        }

        int mid = (left + right) / 2;
        sort(arr, left, mid, depth+1);
        sort(arr, mid+1, right, depth+1);

        // Слияние
        System.arraycopy(arr, left, buffer, left, right-left+1);
        int i = left, j = mid+1, k = left;
        while (i <= mid && j <= right) {
            Metrics.comparisons++;
            if (buffer[i] <= buffer[j]) arr[k++] = buffer[i++];
            else arr[k++] = buffer[j++];
        }
        while (i <= mid) arr[k++] = buffer[i++];
        while (j <= right) arr[k++] = buffer[j++];
    }
}