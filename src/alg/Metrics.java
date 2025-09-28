package alg;

public class Metrics {
    public static int comparisons = 0;
    public static int allocations = 0;
    public static int maxDepth = 0;
    public static long startTime;

    public static void reset() {
        comparisons = 0;
        allocations = 0;
        maxDepth = 0;
        startTime = System.nanoTime();
    }

    public static void print(String algo) {
        long time = System.nanoTime() - startTime;
        System.out.printf("%s: %d comparisons, %d allocations, depth %d, time %.3f ms%n",
                algo, comparisons, allocations, maxDepth, time / 1_000_000.0);
    }
}