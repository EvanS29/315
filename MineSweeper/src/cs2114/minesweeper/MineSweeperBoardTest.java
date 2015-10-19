package cs2114.minesweeper;
import sofia.util.Random;
import junit.framework.TestCase;

//-------------------------------------------------------------------------
/**
*  This is a test class designed to test every instance of every
*  method in the MineSweeperBoard class and make sure that the action
*  is carried out successfully.
*
*  @author Evan Shaw (EvanS29)
*  @version (2015.05.31)
*/

public class MineSweeperBoardTest
    extends TestCase
{
    //~ Instance/static fields ...............................................
    private MineSweeperBoard board;

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Method to check the correctness of the board
     *
     * @param theBoard the actual board
     * @param expected the value expected for the board
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expected)
    {
        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(expected[0].length(), expected.length, 0);
        expectedBoard.loadBoardState(expected);
        assertEquals(expectedBoard, theBoard);
        // uses equals() from MineSweeperBoardBase
    }

    // ----------------------------------------------------------
    /**
     * Test method to test the constructor.
     */
    public void testMineSweeperBoard()
    {
        Random.setNextInts(3, 2, 7, 4);
        board = new MineSweeperBoard(12, 12, 4);
        assertEquals(board.getCell(3, 2), MineSweeperCell.MINE);

        Random.setNextInts(7, 4);
        assertEquals(board.getCell(1, 2), MineSweeperCell.COVERED_CELL);
    }

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        board = new MineSweeperBoard(4, 4, 2);
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure correct cell is uncovered.
     */
    public void testUncoverCell1()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O++O",
                             "OOOO");

        board.uncoverCell(1, 2);

        assertBoard(board, "    ",
                           "OOOO",
                           "O*+O",
                           "OOOO");
        assertTrue(board.isGameLost());
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure correct cell is uncovered.
     */
    public void testUncoverCell2()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "OOOO",
                             "OOOO");

        board.uncoverCell(1, 1);

        assertBoard(board, "    ",
                           "O OO",
                           "OOOO",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure correct cell is uncovered.
     */
    public void testUncoverCell3()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O++O",
                             "OOOO");

        board.uncoverCell(1, 1);

        assertBoard(board, "    ",
                           "O2OO",
                           "O++O",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure correct cell is uncovered.
     */
    public void testUncoverCell4()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O++O",
                             "OOOO");

        board.uncoverCell(-1, 1);

        assertBoard(board, "    ",
                           "OOOO",
                           "O++O",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure correct cell is uncovered.
     */
    public void testUncoverCell5()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O++O",
                             "OOOO");

        board.uncoverCell(1, -1);

        assertBoard(board, "    ",
                           "OOOO",
                           "O++O",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure correct cell is uncovered.
     */
    public void testUncoverCell6()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "OM+O",
                             "OOOO");

        board.uncoverCell(1, 1);

        assertBoard(board, "    ",
                           "O2OO",
                           "OM+O",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure a cell adds a flag or removes a
     * flag.
     */
    public void testFlagCell1()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "OMOO",
                             "OOOO");

        board.flagCell(1, 2);

        assertBoard(board, "    ",
                           "OOOO",
                           "O+OO",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure a cell adds a flag or removes a
     * flag.
     */
    public void testFlagCell2()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "OFOO",
                             "OOOO");

        board.flagCell(1, 2);

        assertBoard(board, "    ",
                           "OOOO",
                           "OOOO",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure a cell adds a flag or removes a
     * flag.
     */
    public void testFlagCell3()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "OOOO",
                             "OOOO");

        board.flagCell(1, 2);

        assertBoard(board, "    ",
                           "OOOO",
                           "OFOO",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to make sure a cell adds a flag or removes a
     * flag.
     */
    public void testFlagCell4()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O+OO",
                             "OOOO");

        board.flagCell(1, 2);

        assertBoard(board, "    ",
                           "OOOO",
                           "OMOO",
                           "OOOO");
    }

    // ----------------------------------------------------------
    /**
     * Test method to test when the game is won.
     */
    public void testIsGameWon1()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "    ",
                             " M  ",
                             "   M");

        assertTrue(board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Test method to test when the game is won.
     */
    public void testIsGameWon2()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState(" +  ",
                             "   F",
                             " M  ",
                             "   F");

        assertFalse(board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Test method to test when the game is won.
     */
    public void testIsGameWon3()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState(" O  ",
                             " * F",
                             " M  ",
                             "   F");

        assertFalse(board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Test method to test when the game is won.
     */
    public void testIsGameWon4()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "   *",
                             " M  ",
                             "   F");

        assertFalse(board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Test method to test when the game is won.
     */
    public void testIsGameWon5()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "   +",
                             " M  ",
                             "   F");

        assertFalse(board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Test method to test when the game is won.
     */
    public void testIsGameWon6()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "   *",
                             "    ",
                             " M F");

        assertFalse(board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Test method to test when the game is won.
     */
    public void testIsGameWon7()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "   +",
                             " +  ",
                             "   F");

        assertFalse(board.isGameWon());
    }

    // ----------------------------------------------------------
    /**
     * Test method to test if the board is uncovered.
     */
    public void testRevealBoard1()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O+OO",
                             "OOOO");

        board.revealBoard();

        assertBoard(board, "    ",
                           "111 ",
                           "1*1 ",
                           "111 ");
    }

    // ----------------------------------------------------------
    /**
     * Test method to test if the board is uncovered.
     */
    public void testRevealBoard2()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "OMOO",
                             "OOOO");

        board.revealBoard();

        assertBoard(board, "    ",
                           "111 ",
                           "1*1 ",
                           "111 ");
    }

    // ----------------------------------------------------------
    /**
     * Test method to test if the board is uncovered.
     */
    public void testRevealBoard3()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOFO",
                             "OMOO",
                             "OOOO");

        board.revealBoard();

        assertBoard(board, "    ",
                           "111 ",
                           "1*1 ",
                           "111 ");
    }

    // ----------------------------------------------------------
    /**
     * Test method to test the SetCell method.
     */
    public void testSetCellIntIntMineSweeperCell()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O++O",
                             "OOOO");

        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);

        assertBoard(board, "    ",
                           "OOOO",
                           "OM+O",
                           "OOOO");
    }

}
