package alg;

public class ClosestPair {
    public static double find(Point[] points) {
        Metrics.reset();
        java.util.Arrays.sort(points, (a, b) -> Double.compare(a.x, b.x));
        double result = find(points, 0, points.length - 1);
        Metrics.print("ClosestPair");
        return result;
    }

    static double find(Point[] points, int left, int right) {
        if (left >= right) return Double.MAX_VALUE;
        if (right - left <= 3) {
            double min = Double.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    Metrics.comparisons++;
                    double d = distance(points[i], points[j]);
                    if (d < min) min = d;
                }
            }
            return min;
        }

        int mid = (left + right) / 2;
        double leftMin = find(points, left, mid);
        double rightMin = find(points, mid + 1, right);
        double min = Math.min(leftMin, rightMin);

        double midX = points[mid].x;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].x - midX) < min) {
                for (int j = i + 1; j <= right && j <= i + 7; j++) {
                    if (Math.abs(points[j].x - midX) < min) {
                        Metrics.comparisons++;
                        double d = distance(points[i], points[j]);
                        if (d < min) min = d;
                    }
                }
            }
        }

        return min;
    }

    static double distance(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}