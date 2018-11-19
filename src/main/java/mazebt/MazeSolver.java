package mazebt;

import java.util.*;

public class MazeSolver {
    private static int N = 0;
    private static int E = 1;
    private static int S = 2;
    private static int W = 3;

    static class State {
        State(MazeIter iter) {
            this.iter = iter;
            seen.add(coord(0,0)); // origin seen
        }
        int facing = 0;
        MazeIter iter;
        int relX = 0;
        int relY = 0;
        Set<AbstractMap.SimpleImmutableEntry> seen = new HashSet<>();
        List<Integer> curPath = new ArrayList<>();
    }

    public static boolean solve(MazeIter iter) {
        List<List<Integer>> queue = new ArrayList<>();
        State s = new State(iter);


        for (int i = 0; i < 4; i++) {
            queue.add(Collections.singletonList(i));
        }

        while (queue.size() > 0) {
            List<Integer> tPath = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            // compare tPath to lastTakenPath and backtrack accordingly
            for (int i=s.curPath.size() - 1; i > getPrefixLen(s.curPath, tPath) + 1; i--) {
                boolean m = move(s, s.curPath.get(i) + 2); // undo moves, always succeeds
                if (!m) {
                    throw new RuntimeException("unable to backtrack, this should not happen unless non bi-di walls");
                }
            }
            for (int i=getPrefixLen(s.curPath, tPath); i < tPath.size(); i++) {
                boolean m = move(s, tPath.get(i));
                if (m && i == tPath.size() - 1) {
                    for (int dir = 0; dir < 4; dir++) {
                        if (wouldCycle(s, dir)) { continue; } // skip routes that loop back onto where we've been already
                        List<Integer> newl = new ArrayList<>(tPath);
                        newl.add(dir);
                        queue.add(newl);
                    }
                }
            }
            if (iter.atGoal()) {
                return true;
            }
        }

        return false;
    }

    static boolean move(State s, int dir) {
        dir = dir % 4;
        s.iter.turnRight(dir - s.facing + 4);
        s.facing += dir - s.facing + 4;
        s.facing = s.facing % 4;
        boolean m = s.iter.forward();
        if (!m) {
            return false;
        }
        updateCoords(s, dir);
        if (s.curPath.size() > 0 && (s.curPath.get(s.curPath.size() - 1) + 2) % 4 == dir) {
            // this is a backtrack
            s.curPath.remove(s.curPath.size() - 1);
        } else {
            s.curPath.add(dir);
        }
        return true;
    }

    private static int getPrefixLen(List<Integer> a, List<Integer> b) {
        for (int i=0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return i;
            }
        }
        return Math.min(a.size(), b.size());
    }

    private static boolean wouldCycle(State s, int dir) {
        int x = s.relX;
        int y = s.relY;
        if (dir == 0) { y++; }
        if (dir == 1) { x++; }
        if (dir == 2) { y--; }
        if (dir == 3) { x--; }
        return s.seen.contains(coord(x,y));
    }

    private static void updateCoords(State s, int dir) {
        if (dir == 0) { s.relY++; }
        if (dir == 1) { s.relX++; }
        if (dir == 2) { s.relY--; }
        if (dir == 3) { s.relX--; }
        s.seen.add(coord(s.relX,s.relY));
    }

    private static AbstractMap.SimpleImmutableEntry coord(int x, int y) {
        return new AbstractMap.SimpleImmutableEntry(x,y);
    }
}
