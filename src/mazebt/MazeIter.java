package mazebt;

import javafx.util.Pair;

import java.util.HashMap;

public class MazeIter {
    int x = 3;
    int y = 3;
    int goalx = 4;
    int goaly = 4;
    int facing = 0;
    HashMap<Pair<Integer,Integer>, Byte> walls = new HashMap<>();
    private int xmin = 0;
    private int xmax = 6;
    private int ymin = 0;
    private int ymax = 6;

    public static byte N=1<<0;
    public static byte E=1<<1;
    public static byte S=1<<2;
    public static byte W=1<<3;

    public boolean forward() {
        Pair<Integer, Integer> p = new Pair<>(x,y);
        if (((walls.get(p) >>  this.facing) & 1) == 1) { return false; }
        if (this.facing == 0) {
            if (y >= ymax) { return false; }
            y++;
        }
        if (this.facing == 1) {
            if (x >= xmax) { return false; }
            x++;
        }
        if (this.facing == 2) {
            if (y <= ymin) { return false; }
            y--;
        }
        if (this.facing == 3) {
            if (x <= xmin) { return false; }
            x--;
        }
        return true;
    }

    public boolean atGoal() {
        return x == goalx && y == goaly;
    }

    public void turnRight(int n) {
        facing += n;
        facing = facing % 4;
    }
}
