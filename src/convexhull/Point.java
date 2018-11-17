package convexhull;

public class Point {
    int x;
    int y;

    public static Point of(int x, int y) {
        Point p = new Point();
        p.x = x;
        p.y = y;
        return p;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
