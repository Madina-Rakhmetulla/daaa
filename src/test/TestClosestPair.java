package test;
import alg.ClosestPair;
import alg.Point;
import java.util.Random;

public class TestClosestPair {
    public static void main(String[] args) {
        testBasic();
        testHorizontal();
        testVertical();
        testRandomSmall();
        testCompareBruteForce();
        System.out.println("All ClosestPair tests passed! ✓");
    }

    static void testBasic() {
        Point[] points = {
                new Point(0, 0), new Point(1, 1),
                new Point(3, 3), new Point(4, 4)
        };
        double result = ClosestPair.find(points);
        double expected = Math.sqrt(2); // расстояние между (0,0) и (1,1)
        assert Math.abs(result - expected) < 1e-9 : "Basic test failed";
    }

    static void testHorizontal() {
        Point[] points = {
                new Point(0, 0), new Point(1, 0),
                new Point(3, 0), new Point(4, 0)
        };
        double result = ClosestPair.find(points);
        assert Math.abs(result - 1.0) < 1e-9 : "Horizontal test failed";
    }

    static void testVertical() {
        Point[] points = {
                new Point(0, 0), new Point(0, 1),
                new Point(0, 3), new Point(0, 4)
        };
        double result = ClosestPair.find(points);
        assert Math.abs(result - 1.0) < 1e-9 : "Vertical test failed";
    }

    static void testCompareBruteForce() {
        Random rand = new Random(42);
        for (int i = 0; i < 10; i++) {
            Point[] points = new Point[50]; // маленький n для проверки
            for (int j = 0; j < 50; j++) {
                points[j] = new Point(rand.nextDouble() * 100, rand.nextDouble() * 100);
            }

            double result = ClosestPair.find(points);
            double brute = bruteForce(points);

            assert Math.abs(result - brute) < 1e-9 :
                    String.format("Validation failed: %.6f vs %.6f", result, brute);
        }
    }

    static void testRandomSmall() {
        Random rand = new Random(42);
        Point[] points = new Point[10];
        for (int i = 0; i < 10; i++) {
            points[i] = new Point(rand.nextDouble() * 10, rand.nextDouble() * 10);
        }
        double result = ClosestPair.find(points);
        double brute = bruteForce(points);
        assert Math.abs(result - brute) < 1e-9 : "Random small test failed";
    }

    static double bruteForce(Point[] points) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double d = distance(points[i], points[j]);
                if (d < min) min = d;
            }
        }
        return min;
    }

    static double distance(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
