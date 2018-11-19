package convexhull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Hull {

    static public List<Point> outerTrees(Point[] points) {
//        if (points.length <= 4) {
//            return Arrays.asList(points);
//        }

        Point start = lowerLeft(points);
        Point vert = new Point();
        vert.x = start.x;
        vert.y = start.y - 1;

        // sort points by angle from vertical
        Arrays.sort(points, ((p, q) -> {
            int a = angle(start, p, q)  - angle(start, q, p);
            if (a == 0) {
                return distance(start, p) - distance(start, q);
            }
            System.out.println(String.format("Val for point %s,%s = %s", p,q, a));
            return a;
        }));
        System.out.println(Arrays.toString(points));

        return null;
    }

    // difference in angle between line formed from p, q, and q,r
    // derived from tan(a) - tan(b)
    static int angle(Point p, Point q, Point r) {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }

    static int distance(Point p, Point q) {
        return (p.x - q.x) * (p.x - q.x) + (p.y - q.y) * (p.y - q.y);
    }

    static Point lowerLeft(Point[] points) {
        Point lowerLeft = points[0];
        for(Point p : points) {
            if (p.x < lowerLeft.x || (p.x == lowerLeft.x && p.y < lowerLeft.y)) {
                lowerLeft = p;
            }
        }
        return lowerLeft;
    }

    public static void main(String[] args) {
        Point[] pts = {
                Point.of(0,0),
                Point.of(1,4),
                Point.of(2,8),
                Point.of(2,9),
                Point.of(3,4),
                Point.of(6,-1),
                Point.of(5,2),
                Point.of(5,1),
                Point.of(6,0),
       };

        outerTrees(pts);
    }
}
