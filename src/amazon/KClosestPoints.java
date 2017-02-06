package amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by billjyc on 2017/1/19.
 */
public class KClosestPoints {
    public Point[] getKNearestPoints(Point[] points, int k) {
        /*if(k > points.length) {
            return points;
        }*/
        Point origin = new Point(0,0);
        Point[] result = new Point[Math.min(k, points.length)];
        if(result.length == 0) {
            return result;
        }
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Double.compare(distance(o2, origin), distance(o1, origin));
            }
        });
        for(Point p : points) {
            /*if(pq.size() >= k) {
                if(distance(p, origin) < distance(pq.peek(), origin)) {
                    pq.poll();
                    pq.offer(p);
                }
            } else {
                pq.offer(p);
            }*/
            pq.offer(p);
        }
        while(pq.size() > k) {
            pq.poll();
        }
        for(int i = 0; i < result.length; i++) {
            result[result.length - 1 - i] = pq.poll();
        }
        return result;
    }

    private double distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public static void main(String[] argc) {
        KClosestPoints s = new KClosestPoints();

        // Test case 1
        Point[] test1 = new Point[5];
        test1[0] = new Point(0, 1);
        test1[1] = new Point(7, 8);
        test1[2] = new Point(2, 6);
        test1[3] = new Point(3, 7);
        test1[4] = new Point(-2, 8);
        Point[] results = s.getKNearestPoints(test1, 3);
        if (results[0] == test1[0] && results[1] == test1[2] && results[2] == test1[3]) {
            System.out.println("test case 1 correct!");
        } else {
            System.out.println("test case 1 wrong!");
        }
        // s.print(results);

        // Test case 2
        Point[] test2 = new Point[4];
        test2[0] = new Point(0, 4);
        test2[1] = new Point(4, 0);
        test2[2] = new Point(0, -4);
        test2[3] = new Point(-4, 0);
        results = s.getKNearestPoints(test2, 3);
        if (results[0] == test2[1] && results[1] == test2[2] && results[2] == test2[3]) {
            System.out.println("test case 2 correct!");
        } else {
            System.out.println("test case 2 wrong!");
        }
        // s.print(results);

        // Test case 3
        Point[] test3 = new Point[3];
        test3[0] = new Point(0, 4);
        test3[1] = new Point(0, 2);
        test3[2] = new Point(0, 1);
        results = s.getKNearestPoints(test3, 5);
        if (results[0] == test3[2] && results[1] == test3[1] && results[2] == test3[0]) {
            System.out.println("test case 3 correct!");
        } else {
            System.out.println("test case 3 wrong!");
        }
        // s.print(results);

        // Test case 4
        Point[] test4 = new Point[0];
        results = s.getKNearestPoints(test4, 8);
        if (results.length == 0) {
            System.out.println("test case 4 correct!");
        } else {
            System.out.println("test case 4 wrong!");
        }
        // s.print(results);

        // Test case 5
        Point[] test5 = new Point[2];
        test5[0] = new Point(0, 1);
        test5[1] = new Point(1, 0);
        results = s.getKNearestPoints(test5, 0);
        if (results.length == 0) {
            System.out.println("test case 5 correct!");
        } else {
            System.out.println("test case 5 wrong!");
        }
    }
}

class Point {
    double x;
    double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
