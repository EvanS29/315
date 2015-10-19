package cs2114.minesweeper;
import sofia.util.Random;

//-------------------------------------------------------------------------
/**
*  This class is the board for the mine sweeper game
*  It's purpose is to supply the layout for all of the game objects and
*  controls
*
*  @author Evan Shaw (EvanS29)
*  @version (2015.05.31)
*/
public class MineSweeperBoard extends MineSweeperBoardBase
{
    //~ Instance/static fields ...............................................
    private int high;
    private int wide;
    private MineSweeperCell[][] board;
    private boolean gameOver;

    //~ Constructor ..........................................................
    /**
     * Creates a new MineSweeperBoard object.
     *
     * @param width the width of the board
     * @param height the height of the board
     * @param mineNum the number of mines on the board
     */
    public MineSweeperBoard(int width, int height, int mineNum)
    {
        this.wide = width;
        this.high = height;
        gameOver = false;
        board = new MineSweeperCell[width][height];
        //Creates the board with all cells covered
        //Iterates through the board at the X coordinates
        for (int x = 0; x < width; x++)
        {
            //Iterates through the board at the X coordinates
            for (int y = 0; y < height; y++)
            {
                setCell(x, y, MineSweeperCell.COVERED_CELL);
            }
        }
        Random rando = Random.generator();
        int count = 0;
        //Adds random mines to the mine sweeper board
        while (count < mineNum)
        {
            int ranX = rando.nextInt(width);
            int ranY = rando.nextInt(height);
            //Does not add mines if a mine already exists at
            //coordinates
            if (board[ranX][ranY] != MineSweeperCell.MINE)
            {
                setCell(ranX, ranY, MineSweeperCell.MINE);
                count++;
            }
        }
    }

    //~ Methods ...............................................................
    // ----------------------------------------------------------
    /**
     * Method to add a flag if no flag currently exists, or to remove a
     * flag if a flag is already at the specified coordinates.
     *
     * @param xValue the X coordinate of the board
     * @param yValue the Y coordinate of the board
     */
    @Override
    public void flagCell(int xValue, int yValue)
    {
        //Puts a flag on a cell if it is covered and does not already have
        //a flag and does not contain a mine
        if (getCell(xValue, yValue) == MineSweeperCell.COVERED_CELL)
        {
            board[xValue][yValue] = MineSweeperCell.FLAG;
        }
        //Puts a flag on a cell if it is covered and does not already have
        //a flag and does contain a mine
        else if (getCell(xValue, yValue) == MineSweeperCell.MINE)
        {
            board[xValue][yValue] = MineSweeperCell.FLAGGED_MINE;
        }
        //Removes a flag from a cell if it is covered cell and does
        //contain a mine and already has a flag placed on it
        else if (getCell(xValue, yValue) == MineSweeperCell.FLAGGED_MINE)
        {
            board[xValue][yValue] = MineSweeperCell.MINE;
        }
        //Removes a flag from a cell if it is covered cell and does not
        //contain a mine and already has a flag placed on it
        else //if (getCell(xValue, yValue) == MineSweeperCell.FLAG)
        {
            board[xValue][yValue] = MineSweeperCell.COVERED_CELL;
        }
    }

    // ----------------------------------------------------------
    /**
     * Method to add a flag if no flag currently exists, or to remove a
     * flag if a flag is already at the specified coordinates.
     *
     * @param xValue the X coordinate of the board
     * @param yValue the Y coordinate of the board
     * @return board[xValue][yValue] gives the coordinates of a specific
     * cell
     */
    @Override
    public MineSweeperCell getCell(int xValue, int yValue)
    {
        //Makes sure that the cell is valid or tells if
        //it is invalid
        if (xValue < 0 || xValue >= wide)
        {
            return MineSweeperCell.INVALID_CELL;
        }
        else if (yValue < 0 || yValue >= high)
        {
            return MineSweeperCell.INVALID_CELL;
        }
        else
        {
            return board[xValue][yValue];
        }
    }

    // ----------------------------------------------------------
    /**
     * This is a method to return the board's height.
     * @return the board's height
     */
    @Override
    public int height()
    {
        return high;
    }

    // ----------------------------------------------------------
    /**
     * This is a method to let user know if game has been lost.
     *
     * @return gameOver game is over
     */
    @Override
    public boolean isGameLost()
    {
        return gameOver;
    }

    // ----------------------------------------------------------
    /**
     * This is a method to let user know if game has been won.
     *
     * @return true the game has been won.
     */
    @Override
    public boolean isGameWon()
    {
        //Checks the cells to see if that the conditions are met for the
        //game to be won.
        //Iterates through the board at the X coordinates
        for (int xValue = 0; xValue < wide; xValue++)
        {
            //Iterates through the board at the Y coordinates
            for (int yValue = 0; yValue < high; yValue++)
            {
                //Checks if there is a flag, mine, or covered cell because
                //if so, the game has not been won.
                if ((getCell(xValue, yValue) == MineSweeperCell.FLAG)
                    || (getCell(xValue, yValue) == MineSweeperCell.MINE)
                        || (getCell(xValue, yValue)
                            == MineSweeperCell.COVERED_CELL)
                                || (getCell(xValue, yValue)
                                    == MineSweeperCell.UNCOVERED_MINE))
                {
                    return false;
                }
            }
        }

        return true;
    }

    // ----------------------------------------------------------
    /**
     * Method to tell the number of mines adjacent to the selected cell
     *
     * @param xValue the X coordinate of the board
     * @param yValue the Y coordinate of the board
     * @return numberAdjacent the number of mines adjacent to the
     * selected cell
     */
    @Override
    public int numberOfAdjacentMines(int xValue, int yValue)
    {
        int numberAdjacent = 0;
        //Uncovers covered and/or flagged cells
        //Iterates through the board at the a values checking the X
        //values before and after the selected X value
        for (int a = xValue - 1; a <= xValue + 1; a++)
        {
            //Iterates through the board at the b values checking the Y
            //values before and after the selected Y value
            for (int b = yValue - 1; b <= yValue + 1; b++)
            {
                //Checks the adjacent cells to count up the number
                //of mines adjacent to the selected cell
                if (getCell(a, b) == MineSweeperCell.FLAGGED_MINE ||
                    getCell(a, b) == MineSweeperCell.MINE ||
                    getCell(a, b) == MineSweeperCell.UNCOVERED_MINE)
                {
                    numberAdjacent++;
                }
            }
        }
        return numberAdjacent;
    }

    // ----------------------------------------------------------
    /**
     * Method that uncovers all of the cells of the board
     */
    @Override
    public void revealBoard()
    {
        //Uncovers covered and/or flagged cells
        //Iterates through the board at the X coordinates
        for (int xValue = 0; xValue < wide; xValue++)
        {
            //Iterates through the board at the Y coordinates
            for (int yValue = 0; yValue < high; yValue++)
            {
                //Uncovers flagged and covered mines and make them
                //uncovered mines
                if (getCell(xValue, yValue) == MineSweeperCell.MINE
                    || getCell(xValue, yValue)
                    == MineSweeperCell.FLAGGED_MINE)
                {
                    board[xValue][yValue] = MineSweeperCell.UNCOVERED_MINE;
                }
                //Uncovers flagged and covered cells and make them
                //uncovered by showing the number of mines adjacent
                //to the uncovered cell, or just being uncovered if
                //no other mines are adjacent to it
                if (getCell(xValue, yValue) == MineSweeperCell.COVERED_CELL
                    || getCell(xValue, yValue) == MineSweeperCell.FLAG)
                {
                    int adjacentMines = numberOfAdjacentMines(xValue, yValue);
                    board[xValue][yValue] =
                        MineSweeperCell.adjacentTo(adjacentMines);
                }
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * Method that sets the new cell value of a cell
     *
     * @param xValue the X coordinate of the board
     * @param yValue the Y coordinate of the board
     * @param cellValue the value of the cell such as covered, uncovered,
     * flagged or if the cell contains a mine
     */
    @Override
    protected void setCell(int xValue, int yValue, MineSweeperCell cellValue)
    {
        board[xValue][yValue] = cellValue;
    }

    // ----------------------------------------------------------
    /**
     * Method that uncovers a cell on the board
     *
     * @param xValue the X coordinate of the board
     * @param yValue the Y coordinate of the board
     */
    @Override
    public void uncoverCell(int xValue, int yValue)
    {
        //Uncovers cells at coordinates that are valid cells
        if (getCell(xValue, yValue) != MineSweeperCell.INVALID_CELL)
        {
            //Uncovers a covered cell to reveal the number of mines in
            //adjacent cells
            if (getCell(xValue, yValue) == MineSweeperCell.COVERED_CELL)
            {
                int adjacentMines = numberOfAdjacentMines(xValue, yValue);
                board[xValue][yValue] =
                    MineSweeperCell.adjacentTo(adjacentMines);
            }
            //Uncovers a covered cell containing a mine to end the game
            if (getCell(xValue, yValue) == MineSweeperCell.MINE)
            {
                board[xValue][yValue] = MineSweeperCell.UNCOVERED_MINE;
                gameOver = true;
            }
        }
    }

    // ----------------------------------------------------------
    /**
     * This is a method to return the board's width.
     *
     * @return wide the board's width
     */
    @Override
    public int width()
    {
        return wide;
    }

}
