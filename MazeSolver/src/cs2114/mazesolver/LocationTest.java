package cs2114.mazesolver;

import junit.framework.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the methods of the Maze class
 *
 * @author Evan Shaw (evans29)
 * @version 2015.06.09
 */

public class LocationTest
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
     * Tests east method.
     */
    public void testEast()
    {
        assertEquals(new Location(3, 3), area.east());
    }


    // ----------------------------------------------------------
    /**
     * Tests north method.
     */
    public void testNorth()
    {
        assertEquals(new Location(2, 2), area.north());
    }


    // ----------------------------------------------------------
    /**
     * Tests south method.
     */
    public void testSouth()
    {
        assertEquals(new Location(2, 4), area.south());
    }


    // ----------------------------------------------------------
    /**
     * Tests west method.
     */
    public void testWest()
    {
        assertEquals(new Location(1, 3), area.west());
    }


    // ----------------------------------------------------------
    /**
     * Tests equals method.
     */
    public void testEqualsObject()
    {
        Location place = new Location(2, 3);
        assertTrue(area.equals(place));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals method.
     */
    public void testEqualsObject2()
    {
        Location place2 = new Location(3, 3);
        assertFalse(area.equals(place2));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals method.
     */
    public void testEqualsObject3()
    {
        Location place = new Location(2, 4);
        assertFalse(area.equals(place));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals method.
     */
    public void testEqualsObject4()
    {
        Location place = new Location(4, 4);
        assertFalse(area.equals(place));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals method.
     */
    public void testEqualsObject5()
    {
        assertFalse(area.equals(board));
    }


    // ----------------------------------------------------------
    /**
     * Tests the toString method.
     */
    public void testToString()
    {
        assertEquals("(2, 3)", area.toString());
    }

}
