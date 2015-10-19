package com.example.connectfour;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the methods of the Board class
 *
 * @author Evan Shaw (EvanS29)
 * @version (2015.06.30)
 */

public class BoardTest
    extends TestCase
{
    private Board c4Board;


    // ----------------------------------------------------------
    /**
     * Sets up the test fixture. Called before every test case method.
     */
    public void setUp()
    {
        c4Board = new Board();
    }


    // ----------------------------------------------------------
    /**
     * Tests get cell method.
     */
    public void testGetCell()
    {
        Location area = new Location(2, 3);
        assertEquals(BoardState.INVALID_SPOT, c4Board.getCell(area));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell()
    {
        Location outOfBounds = new Location(-3, 5);
        assertEquals(BoardState.INVALID_SPOT, c4Board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell2()
    {
        Location outOfBounds = new Location(3, -5);
        assertEquals(BoardState.INVALID_SPOT, c4Board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell3()
    {
        Location outOfBounds = new Location(11, 3);
        assertEquals(BoardState.INVALID_SPOT, c4Board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell4()
    {
        Location outOfBounds = new Location(3, 10);
        assertEquals(BoardState.INVALID_SPOT, c4Board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests set cell method.
     */
    public void testSetCell5()
    {
        Location outOfBounds = new Location(3, 10);
        assertEquals(BoardState.INVALID_SPOT, c4Board.getCell(outOfBounds));
    }


    // ----------------------------------------------------------
    /**
     * Tests the check Horizonatally method.
     */
    public void testCheckHorizontally()
    {
        assertTrue(c4Board.isPlayerOne());

        Location area = new Location(2, 6);
        c4Board.placeCoin(area);
        assertTrue(c4Board.isPlayerTwo());

        Location area2 = new Location(2, 5);
        c4Board.placeCoin(area2);
        assertTrue(c4Board.isPlayerOne());

        Location area3 = new Location(3, 6);
        c4Board.placeCoin(area3);
        assertTrue(c4Board.isPlayerTwo());

        Location area4 = new Location(2, 4);
        c4Board.placeCoin(area4);
        assertTrue(c4Board.isPlayerOne());

        Location area5 = new Location(4, 6);
        c4Board.placeCoin(area5);
        assertTrue(c4Board.isPlayerTwo());

        Location area6 = new Location(2, 3);
        c4Board.placeCoin(area6);
        assertTrue(c4Board.isPlayerOne());

        Location area7 = new Location(5, 6);
        c4Board.placeCoin(area7);
        assertTrue(c4Board.isPlayerTwo());
        assertEquals("Player 1 wins!", c4Board.checkHorizontally());
    }


    // ----------------------------------------------------------
    /**
     * Tests the check Vertically method.
     */
    public void testCheckVertically()
    {
        assertTrue(c4Board.isPlayerOne());

        Location area = new Location(2, 6);
        c4Board.placeCoin(area);
        assertTrue(c4Board.isPlayerTwo());

        Location area2 = new Location(2, 5);
        c4Board.placeCoin(area2);
        assertTrue(c4Board.isPlayerOne());

        Location area3 = new Location(3, 6);
        c4Board.placeCoin(area3);
        assertTrue(c4Board.isPlayerTwo());

        Location area4 = new Location(2, 4);
        c4Board.placeCoin(area4);
        assertTrue(c4Board.isPlayerOne());

        Location area5 = new Location(4, 6);
        c4Board.placeCoin(area5);
        assertTrue(c4Board.isPlayerTwo());

        Location area6 = new Location(2, 3);
        c4Board.placeCoin(area6);
        assertTrue(c4Board.isPlayerOne());

        Location area7 = new Location(1, 6);
        c4Board.placeCoin(area7);
        assertTrue(c4Board.isPlayerTwo());

        Location area8 = new Location(2, 2);
        c4Board.placeCoin(area8);
        assertTrue(c4Board.isPlayerOne());

        assertEquals("Player 2 wins!", c4Board.checkVertically());
    }


    // ----------------------------------------------------------
    /**
     * Tests the moves made method.
     */
    public void testMovesMade()
    {
        Location area = new Location(2, 6);
        c4Board.placeCoin(area);
        assertTrue(c4Board.isPlayerTwo());

        Location area2 = new Location(2, 5);
        c4Board.placeCoin(area2);
        assertTrue(c4Board.isPlayerOne());

        Location area3 = new Location(3, 6);
        c4Board.placeCoin(area3);
        assertTrue(c4Board.isPlayerTwo());

        Location area4 = new Location(1, 6);
        c4Board.placeCoin(area4);
        assertTrue(c4Board.isPlayerOne());

        Location area5 = new Location(3, 5);
        c4Board.placeCoin(area5);

        String stringPOne = "Player One: (3, 5)(3, 6)(2, 6)";

        String stringPTwo = "Player Two: (1, 6)(2, 5)";

        assertEquals(stringPOne, c4Board.playerOneMoves());
        assertEquals(stringPTwo, c4Board.playerTwoMoves());
    }
}
