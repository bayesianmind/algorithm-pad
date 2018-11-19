package mazebt;

import java.util.AbstractMap;
import java.util.HashMap;

public class MazeIter {
    int x = 2;
    int y = 2;
    int goalx = 0;
    int goaly = 0;
    int facing = 0;
    HashMap<AbstractMap.SimpleImmutableEntry, Integer> walls = new HashMap<>();
    private int xmin = 0;
    private int xmax = 4;
    private int ymin = 0;
    private int ymax = 4;

    public MazeIter(int x, int y, int goalx, int goaly) {
        this.x = x;
        this.y = y;
        this.goalx = goalx;
        this.goaly = goaly;
    }

    public static Integer N=1<<0;
    public static Integer E=1<<1;
    public static Integer S=1<<2;
    public static Integer W=1<<3;
    private static Integer NOWALL=1<<4;

    public boolean forward() {
        AbstractMap.SimpleImmutableEntry p = coord(x,y);
        if (((walls.getOrDefault(p, NOWALL) >>  this.facing) & 1) == 1) { return false; }
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

    static AbstractMap.SimpleImmutableEntry coord(int x, int y) {
        return new AbstractMap.SimpleImmutableEntry(x,y);
    }
}
