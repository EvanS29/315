package cs2114.mazesolver;

import android.widget.*;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * Tests the maze solver screen class for the maze solver game.
 *
 * @author Evan Shaw (evans29)
 * @version 2015.06.16
 */
public class MazeSolverScreenTests
    extends student.AndroidTestCase<MazeSolverScreen>
{
    // ~ Fields ................................................................

    private ShapeView        shapeView;
    // private TextView infoLabel;

    // This field will store the pixel width/height of a cell in the maze.
    private int              cellSize;
    private MazeSolverScreen screen;


    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public MazeSolverScreenTests()
    {
        super(MazeSolverScreen.class);
    }


    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Initializes the text fixtures.
     */
    public void setUp()
    {
        screen = this.getScreen();
        float viewSize = Math.min(shapeView.getWidth(), shapeView.getHeight());
        cellSize = (int)(viewSize / 10);
    }


    /**
     * Test method for the draw method
     */
    public void testDraw()
    {
        screen.drawWallsClicked();
        clickCell(0, 0);
        clickCell(9, 9);
        clickCell(4, 2);
        MazeCell mazeCell = screen.getMaze().getCell(new Location(4, 2));
        assertEquals(mazeCell, MazeCell.WALL);
    }


    /**
     * Test method for the erase method
     */
    public void testErase()
    {
        testDraw();
        screen.eraseWallsClicked();
        clickCell(0, 0);
        clickCell(9, 9);
        clickCell(4, 2);
        MazeCell mazeCell = screen.getMaze().getCell(new Location(4, 2));
        assertEquals(mazeCell, MazeCell.UNEXPLORED);

    }


    /**
     * Test method for the setStart method
     */
    public void testStart()
    {
        screen.setStartClicked();
        Location test1 = new Location(9, 9);
        clickCell(test1.x(), test1.y());

        ILocation test2 = screen.getMaze().getStartLocation();
        assertEquals(test1, test2);

        MazeCell mazeCell = screen.getMaze().getCell(test1);
        assertEquals(mazeCell, MazeCell.UNEXPLORED);

    }


    /**
     * Test method for the setGoal method
     */
    public void testGoal()
    {
        screen.setGoalClicked();
        Location test1 = new Location(4, 2);
        clickCell(test1.x(), test1.y());

        ILocation test2 = screen.getMaze().getGoalLocation();
        assertEquals(test1, test2);

        MazeCell mazeCell = screen.getMaze().getCell(test1);
        assertEquals(mazeCell, MazeCell.UNEXPLORED);

    }


    /**
     * Tests the TextView method
     */
    public void testSolve()
    {
        screen.setStartClicked();
        clickCell(9, 0);

        screen.setGoalClicked();
        clickCell(0, 0);

        screen.solveClicked();
        String result =
            "(0, 0)(1, 0)(2, 0)(3, 0)(4, 0)(5, 0)(6, 0)(7, 0)(8, 0)(9, 0)";
        assertEquals(result, screen.infoLabel());
    }


    /**
     * Tests the solve button when there is no solution.
     */
    public void testSolveFalse()
    {
        screen.drawWallsClicked();
        clickCell(0, 1);
        clickCell(1, 0);
        clickCell(1, 1);

        screen.solveClicked();

        String result = "No solution was possible.";
        assertEquals(result, screen.infoLabel());
    }


    /**
     * Tests the touchMoveCell function
     */
    public void testOnTouchMove()
    {
        screen.drawWallsClicked();
        touchDownCell(5, 7);
        touchMoveCell(7, 2);
        touchUp();

        MazeCell mazeCell = screen.getMaze().getCell(new Location(3, 3));
        assertEquals(mazeCell, MazeCell.UNEXPLORED);
    }


    // ~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates moving the finger instantaneously to the middle of the
     * specified cell in the maze.
     */
    private void touchMoveCell(int x, int y)
    {
        touchMove((x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }


    // ----------------------------------------------------------
    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }
}
