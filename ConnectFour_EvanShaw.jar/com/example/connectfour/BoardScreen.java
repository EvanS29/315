package com.example.connectfour;

import android.widget.TextView;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;

// -------------------------------------------------------------------------
/**
 * Maze solver screen class for the maze solver game.
 *
 * @author Evan Shaw (evans29)
 * @version 2015.06.16
 */

public class BoardScreen
    extends ShapeScreen
{
    // ~ Fields ................................................................
    private Board              c4;
    private CoverTile[][]      tileBoard;
    private BoardState         clicked;
    private float              size;
    private float              cellSize;
    private final int          LENGTH = 7;
    private TextView           infoLabel;
    private static final Color P1     = Color.red;
    private static final Color P2     = Color.black;
    private static final Color EMPTY  = Color.yellow;

    // ~ Methods ...............................................................


    // ----------------------------------------------------------
    /**
     * The initialize method is called when the screen is about to be presented
     * to the user. Unlike most classes in Java, we usually do not write
     * constructors for Screen subclasses to do initialization tasks. Instead,
     * we write this initialize() method, which is called only after the system
     * has guaranteed that resources we need are available.
     */
    public void initialize()
    {
        // Create the model that our application will be using.
        c4 = new Board();
        tileBoard = new CoverTile[LENGTH][LENGTH];
        // boardSize = Math.min(wide, high);
        // cellSize = boardSize / LENGTH;
        float wide = getShapeView().getWidth();
        float high = getShapeView().getHeight();
        size = Math.min(wide, high);
        cellSize = (size / 7);
        // Creates the board with all cells covered
        // Iterates through the board at the X coordinates
        for (int x = 0; x < 7; x++)
        {
            // Iterates through the board at the Y coordinates
            for (int y = 0; y < 7; y++)
            {
                CoverTile tile = new CoverTile(
                    x * cellSize + 1,
                    y * cellSize,
                    (x + 1) * cellSize - 1,
                    (y + 1) * cellSize);
                tileBoard[x][y] = tile;
                // this.add(tileBoard[x][y]);
                add(tile);
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Called when the user touches down on the screen.
     *
     * @param x
     *            the x-coordinate where the screen was touched
     * @param y
     *            the y-coordinate where the screen was touched
     */
    public void onTouchDown(float x, float y)
    {
        int xC = (int)Math.floor(x / cellSize);
        int yC = (int)Math.floor(y / cellSize);
        Location here = new Location(xC, yC);
        c4.placeCoin(here);
        if (c4.getCell(here) == BoardState.PLAYERONE_SPOT)
        {
            tileBoard[xC][yC].setFillColor(P1);
            String result = c4.checkHorizontally();
            if (result == null)
                result = c4.checkVertically();
            if (result != null)
                infoLabel.setText(result);
        }

        else if (c4.getCell(here) == BoardState.PLAYERTWO_SPOT)
        {
            tileBoard[xC][yC].setFillColor(P2);
            String result = c4.checkHorizontally();
            if (result == null)
                result = c4.checkVertically();
            if (result != null)
                infoLabel.setText(result);
        }

    }


    // ----------------------------------------------------------
    /**
     * Tells what to do when goal clicked
     */
    public void playerOneMovesClicked()
    {
        String solution = c4.playerOneMoves();
        infoLabel.setText(solution);

    }


    // ----------------------------------------------------------
    /**
     * Tells what to do when goal clicked
     */
    public void playerTwoMovesClicked()
    {
        String solution = c4.playerTwoMoves();
        infoLabel.setText(solution);
    }


    // ----------------------------------------------------------
    /**
     * @return the maze that will be used in testing
     */
    public Board getBoard()
    {
        return c4;
    }


    /**
     * @return Tells the info of the maze solution.
     */
    public String infoLabel()
    {
        return infoLabel.getText().toString();
    }
}
