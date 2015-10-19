package cs2114.mazesolver;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the methods of the Maze class
 *
 * @author Evan Shaw (evans29)
 * @version 2015.06.09
 */

public class MazeTest
    extends TestCase
{
    private Maze     board;
    private Location area;


    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp()
    {
        area = new Location(2, 3);
        board = new Maze(10);
    }


    // ----------------------------------------------------------
    /**
     * Tests get cell method.
     */
    public void testGetCell()
    {
        assertEquals(MazeCell.UNEXPLORED, board.getCell(area));
    }


    // ----------------------------------------------------------
    /**
     * Tests get cell method.
     */
    public void testGetCell2()
    {
        Location ob;
        ob = new Location(12, 11);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(ob));
    }


    // ----------------------------------------------------------
    /**
     * Tests get goal location method.
     */
    public void testGetGoalLocation()
    {
        assertEquals(new Location(9, 9), board.getGoalLocation());
    }


    // ----------------------------------------------------------
    /**
     * Tests get start location method.
     */
    public void testGetStartLocation()
    {
        assertEquals(new Location(0, 0), board.getStartLocation());
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell()
    {
        Location outOfBounds = new Location(-3, 5);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell2()
    {
        assertEquals(MazeCell.UNEXPLORED, board.getCell(area));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell3()
    {
        Location outOfBounds = new Location(3, -5);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell4()
    {
        Location start = new Location(0, 0);
        board.setCell(start, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, board.getCell(start));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell5()
    {
        Location end = new Location(9, 9);
        board.setCell(end, MazeCell.WALL);
        assertEquals(MazeCell.UNEXPLORED, board.getCell(end));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell6()
    {
        Location place = new Location(3, 4);
        board.setCell(place, MazeCell.WALL);
        assertEquals(MazeCell.WALL, board.getCell(place));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell7()
    {
        Location outOfBounds = new Location(3, 11);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell8()
    {
        Location outOfBounds = new Location(3, 10);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell9()
    {
        Location outOfBounds = new Location(10, 3);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell10()
    {
        Location outOfBounds = new Location(11, 3);
        assertEquals(MazeCell.INVALID_CELL, board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set start location method.
     */
    public void testSetStartLocation()
    {

        board.setStartLocation(area);
        assertEquals(new Location(2, 3), board.getStartLocation());
    }


    // ----------------------------------------------------------
    /**
     * Tests set start location method.
     */
    public void testSetStartLocation2()
    {
        board.setCell(area, MazeCell.WALL);
        board.setStartLocation(area);
        assertEquals(MazeCell.UNEXPLORED, board.getCell(area));
    }


    // ----------------------------------------------------------
    /**
     * Tests set goal location method.
     */
    public void testSetGoalLocation()
    {
        board.setGoalLocation(area);
        assertEquals(new Location(2, 3), board.getGoalLocation());
    }


    /**
     * Tests set goal location method.
     */
    public void testSetGoalLocation2()
    {
        board.setCell(area, MazeCell.WALL);
        board.setGoalLocation(area);
        assertEquals(MazeCell.UNEXPLORED, board.getCell(area));
    }


    // ----------------------------------------------------------
    /**
     * Tests size method.
     */
    public void testSize()
    {
        assertEquals(10, board.size());
    }


    // ----------------------------------------------------------
    /**
     * Tests solve method.
     */
    public void testSolve()
    {
        board.setGoalLocation(area);
        board.setStartLocation(new Location(0, 9));
        board.setCell(new Location(1, 8), MazeCell.WALL);
        board.setCell(new Location(0, 8), MazeCell.WALL);
        board.setCell(new Location(1, 9), MazeCell.WALL);
        assertNull(board.solve());
    }


    // ----------------------------------------------------------
    /**
     * Tests solve method.
     */
    public void testSolve2()
    {
        board.setStartLocation(new Location(0, 0));
        board.setGoalLocation(new Location(9, 9));
        String string =
            "(9, 9)(9, 8)(8, 8)(7, 8)(6, 8)(5, 8)(4, 8)(3, 8)(2, 8)(1, 8)"
                + "(0, 8)(0, 7)(1, 7)(2, 7)(3, 7)(4, 7)(5, 7)(6, 7)(7, 7)(8, 7)"
                + "(9, 7)(9, 6)(8, 6)(7, 6)(6, 6)(5, 6)(4, 6)(3, 6)(2, 6)(1, 6)"
                + "(0, 6)(0, 5)(1, 5)(2, 5)(3, 5)(4, 5)(5, 5)(6, 5)(7, 5)(8, 5)"
                + "(9, 5)(9, 4)(8, 4)(7, 4)(6, 4)(5, 4)(4, 4)(3, 4)(2, 4)(1, 4)"
                + "(0, 4)(0, 3)(1, 3)(2, 3)(3, 3)(4, 3)(5, 3)(6, 3)(7, 3)(8, 3)"
                + "(9, 3)(9, 2)(8, 2)(7, 2)(6, 2)(5, 2)(4, 2)(3, 2)(2, 2)(1, 2)"
                + "(0, 2)(0, 1)(1, 1)(2, 1)(3, 1)(4, 1)(5, 1)(6, 1)(7, 1)(8, 1)"
                + "(9, 1)(9, 0)(8, 0)(7, 0)(6, 0)(5, 0)(4, 0)(3, 0)(2, 0)(1, 0)"
                + "(0, 0)";
        assertEquals(string, board.solve());
    }

    // ----------------------------------------------------------
    /**
     * Tests solve method.
     */
    public void testSolve3()
    {
        board.setStartLocation(new Location(9, 0));
        board.setGoalLocation(new Location(0, 0));
        String string =
            "(0, 0)(1, 0)(2, 0)(3, 0)(4, 0)(5, 0)(6, 0)(7, 0)(8, 0)(9, 0)";
        assertEquals(string, board.solve());
    }

    // ----------------------------------------------------------
    /**
     * Tests solve method.
     */
    public void testSolve4()
    {
        board.setGoalLocation(area);
        board.setStartLocation(new Location(0, 9));
        board.setCell(new Location(1, 8), MazeCell.WALL);
        board.setCell(new Location(1, 9), MazeCell.WALL);
        assertNotNull(board.solve());
    }
}
