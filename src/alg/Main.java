package alg;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random(42);
        int n = 1000;

        // Тест MergeSort
        int[] arr1 = new int[n];
        for (int i = 0; i < n; i++) arr1[i] = rand.nextInt(10000);
        MergeSort.sort(arr1.clone());

        // Тест QuickSort
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) arr2[i] = rand.nextInt(10000);
        QuickSort.sort(arr2.clone());

        // Тест DeterministicSelect
        int[] arr3 = new int[n];
        for (int i = 0; i < n; i++) arr3[i] = rand.nextInt(10000);
        int median = DeterministicSelect.select(arr3, n/2);
        System.out.println("Median: " + median);

        // Тест ClosestPair
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(rand.nextDouble()*100, rand.nextDouble()*100);
        }
        double dist = ClosestPair.find(points);
        System.out.printf("Closest distance: %.3f%n", dist);
    }
}