package com.example.connectfour;

import android.widget.*;
import sofia.graphics.ShapeView;

// -------------------------------------------------------------------------
/**
 * Tests the board screen class for the connect four game.
 *
 * @author Evan Shaw (EvanS29)
 * @version (2015.06.30)
 */
public class BoardScreenTest
    extends student.AndroidTestCase<BoardScreen>
{
    private BoardScreen screen;
    private ShapeView   shapeView;


    /**
     * Test cases that extend AndroidTestCase must have a parameterless
     * constructor that calls super() and passes it the screen/activity class
     * being tested.
     */
    public BoardScreenTest()
    {
        super(BoardScreen.class);
    }


    public void setUp()
    {
        screen = this.getScreen();

    }


    /**
     * Tests the TextView method
     */
    public void testSolve()
    {
        screen.onTouchDown(6, 6);
        screen.onTouchDown(3,6);

        screen.playerOneMovesClicked();
        String result =
            "(0, 0)(1, 0)(2, 0)(3, 0)(4, 0)(5, 0)(6, 0)(7, 0)(8, 0)(9, 0)";
        assertEquals(result, screen.infoLabel());
    }


    // ~ Private methods .......................................................
    // ----------------------------------------------------------
    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        // touchDown(shapeView, (x + 0.5f));
    }
}
