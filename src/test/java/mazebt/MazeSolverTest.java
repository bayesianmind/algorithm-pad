package mazebt;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static mazebt.MazeIter.coord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeSolverTest {
    private SoftAssertions assertion;

    @Test
    public void TestBacktracking() {
        MazeIter mi = new MazeIter(2,2,1,1);
        MazeSolver.State s = new MazeSolver.State(mi);
        assertTrue(MazeSolver.move(s, 0));
        assertPosition(mi, 2, 3);
        assertEquals(Arrays.asList(0), s.curPath);
        assertTrue(MazeSolver.move(s, 0));
        assertPosition(mi, 2, 4);
        assertEquals(Arrays.asList(0,0), s.curPath);
        assertTrue(MazeSolver.move(s, 1));
        assertPosition(mi, 3, 4);
        assertEquals(Arrays.asList(0,0,1), s.curPath);
        assertTrue(MazeSolver.move(s, 3));
        assertPosition(mi, 2, 4);
        assertEquals(Arrays.asList(0,0), s.curPath);
        assertTrue(MazeSolver.move(s, 2));
        assertPosition(mi, 2, 3);
        assertEquals(Arrays.asList(0), s.curPath);
        assertTrue(MazeSolver.move(s, 2));
        assertPosition(mi, 2, 2);
        assertEquals(Arrays.asList(), s.curPath);
        assertTrue(MazeSolver.move(s, 2));
        assertPosition(mi, 2, 1);
        assertEquals(Arrays.asList(2), s.curPath);
        assertTrue(MazeSolver.move(s, 2));
        assertPosition(mi, 2, 0);
        assertEquals(Arrays.asList(2,2), s.curPath);
        assertFalse(MazeSolver.move(s, 2));
        assertTrue(MazeSolver.move(s, 0));
        assertTrue(MazeSolver.move(s, 0));
        assertPosition(mi, 2, 2);
        assertEquals(Arrays.asList(), s.curPath);
    }

    @Test
    public void TestEmptyMaze() {
        MazeIter mi = new MazeIter(3,3,0,0);
        solve(mi);
    }

    @Test
    public void TestEightShape() {
        MazeIter mi = new MazeIter(1,2,2,1);
        /*
        | x   |
          - *
         */

        mi.walls.put(coord(1,2), MazeIter.W | MazeIter.S  | MazeIter.N);
        mi.walls.put(coord(2,2), MazeIter.N | MazeIter.E);
        solve(mi);
    }

    @Test
    public void TestWalledOff() {
        MazeIter mi = new MazeIter(1,1,2,3);
        mi.walls.put(coord(1,1), MazeIter.W | MazeIter.S  | MazeIter.N);
        mi.walls.put(coord(2,1), MazeIter.N | MazeIter.E);
        mi.walls.put(coord(2,0), MazeIter.W | MazeIter.E);
        boolean solved = MazeSolver.solve(mi);
        assertFalse(solved);
    }

    @Test
    public void TestSolutionPointOffGrid() {
        MazeIter mi = new MazeIter(0,0,10,10);
        boolean solved = MazeSolver.solve(mi);
        assertFalse(solved);
    }

    void solve(MazeIter mi) {
        boolean solved = MazeSolver.solve(mi);
        assertion.assertThat(solved).isTrue();
        assertion.assertThat(mi.goaly).isEqualTo(mi.y);
        assertion.assertThat(mi.goalx).isEqualTo(mi.x);
    }

    void assertPosition(MazeIter mi, int x, int y) {
        assertEquals(x, mi.x, "x");
        assertEquals(y, mi.y, "y");
    }

    @BeforeEach
    public void setUp() {
        assertion = new SoftAssertions();
    }

    @AfterEach
    public void tearDown() {
        assertion.assertAll();
    }
}