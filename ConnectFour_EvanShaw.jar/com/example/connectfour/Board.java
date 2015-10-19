package com.example.connectfour;

import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * Class of the board for a game similar to Connect Four. The board will
 * contains an empty 7x7 square board. Players will then play by adding red and
 * black pieces to the columns to try to connect 4 in-a-row either horizontally
 * or vertically. With the option of undoing the last made move.
 *
 * @author EvanS29
 * @version 2015.06.29
 */
public class Board
{
    private BoardState[][]  cBoard;
    private int             turn;
    private int             playerTurn;
    private final int       length = 7;
    private Stack<Location> playerOneMovement;
    private Stack<Location> playerTwoMovement;

    static int debug_count;


    // ----------------------------------------------------------
    /**
     * Create a new 7 x 7 Board object.
     */
    public Board()
    {
        turn = 0;
        playerTurn = turn % 2;
        playerOneMovement = new Stack<Location>();
        playerTwoMovement = new Stack<Location>();
        cBoard = new BoardState[length][length];
        // Creates the board with all cells covered
        // Iterates through the board at the X coordinates
        for (int x = 0; x < length; x++)
        {
            // Iterates through the board at the Y coordinates
            for (int y = 0; y < length; y++)
            {
                cBoard[x][y] = BoardState.INVALID_SPOT;
            }
        }
        // Iterates through the board at the X coordinates
        for (int x = 0; x < length; x++)
        {
            cBoard[x][6] = BoardState.PLAYABLE_SPOT;
        }
    }


    // ----------------------------------------------------------
    /**
     * Gets the status of the cell
     *
     * @param spot
     *            the location
     * @return the locations of the maze
     */
    public BoardState getCell(Location spot)
    {
        // Sets cells outside of the range as invalid
        if (spot.x() < 0 || spot.x() >= 7)
        {
            return BoardState.INVALID_SPOT;
        }
        else if (spot.y() < 0 || spot.y() >= 7)
        {
            return BoardState.INVALID_SPOT;
        }
        // else sets the location clicked on to return the spot
        else
        {
            return cBoard[spot.x()][spot.y()];
        }
    }


    // ----------------------------------------------------------
    /**
     * Sets the status of the cell
     *
     * @param spot
     *            the location of the button pressed
     * @param state
     *            the state of the board
     */
    public void setCell(Location spot, BoardState state)
    {
        cBoard[spot.x()][spot.y()] = state;
    }


    // ----------------------------------------------------------
    /**
     * Method to place the checker in a valid location
     *
     * @param spot
     *            the location of the area where the checker piece is to be
     *            played
     */
    public void placeCoin(Location spot)
    {
        debug_count += 1;
        // if it is a valid playing state it changes board state to the player's
        // move and after each go it switches players and makes the cell above
        // it a playable cell
        if (cBoard[spot.x()][spot.y()] == BoardState.PLAYABLE_SPOT
            && getCell(spot.south()) != BoardState.PLAYABLE_SPOT)
        {
            // if it is player one's turn changes location of his move to his
            // spot and adds his move to his stack
            if (isPlayerOne())
            {
                playerOneMovement.push(spot);
                cBoard[spot.x()][spot.y()] = BoardState.PLAYERONE_SPOT;
            }
            // if it is player two's turn changes location of his move to his
            // spot and adds his move to his stack
            else
            {
                playerTwoMovement.push(spot);
                cBoard[spot.x()][spot.y()] = BoardState.PLAYERTWO_SPOT;
            }
            turn++;
            playerTurn = turn % 2;
            setCell(spot.north(), BoardState.PLAYABLE_SPOT);
            checkHorizontally();
            checkVertically();
            System.out.println("[" + debug_count + "] coin placed");
        }
        System.out.println("[" + debug_count + "] turn: " + turn);
    }


    // ----------------------------------------------------------
    /**
     * Method to indicate player 1
     *
     * @return even integer
     */
    public boolean isPlayerOne()
    {
        return (playerTurn == 0);
    }


    // ----------------------------------------------------------
    /**
     * Method to indicate player 2
     *
     * @return odd integer
     */
    public boolean isPlayerTwo()
    {
        return (playerTurn == 1);
    }


    // ----------------------------------------------------------
    /**
     * Method to check, horizontally for 4 of the same color checkers in a row
     *
     * @return the winner of the game if there is one
     */
    public String checkHorizontally()
    {
        String winner = null;
        int redCount = 0;
        int blackCount = 0;
        // Iterates through the board at the X coordinates
        for (int row = 0; row < length; ++row)
        {
            // Iterates through the board at the Y coordinates
            for (int column = 0; column < length; ++column)
            {
                // if it finds a move of player one it adds to the count and
                // makes the other player's count reset
                if (cBoard[row][column] == BoardState.PLAYERONE_SPOT)
                {
                    redCount++;
                    blackCount = 0;
                }

                else if (cBoard[row][column] == BoardState.PLAYERTWO_SPOT)
                {
                    blackCount++;
                    redCount = 0;
                }
                // if it finds 4 reds in a row player 1 wins
                if (redCount == 4)
                {
                    winner = "Player 1 wins!";
                }
                // if it finds 4 blacks in a row player 2 wins
                if (blackCount == 4)
                {
                    winner = "Player 2 wins!";
                }
            }
        }
        return (winner);
    }


    // ----------------------------------------------------------
    /**
     * Method to check vertically for 4 of the same color checkers in a row
     *
     * @return the winner of the game if there is one
     */
    public String checkVertically()
    {
        String winner = null;
        int redCount = 0;
        int blackCount = 0;
        // Iterates through the board at the X coordinates
        for (int row = 0; row < length; ++row)
        {
            // Iterates through the board at the Y coordinates
            for (int column = 0; column < length; ++column)
            {
                // if it finds a move of player one it adds to the count and
                // makes the other player's count reset
                if (cBoard[row][column] == BoardState.PLAYERONE_SPOT)
                {
                    redCount++;
                    blackCount = 0;
                }

                else if (cBoard[row][column] == BoardState.PLAYERTWO_SPOT)
                {
                    blackCount++;
                    redCount = 0;
                }
                // if it finds 4 reds in a row player 1 wins
                if (redCount == 4)
                {
                    winner = "Player 1 wins!";
                }
                // if it finds 4 blacks in a row player 2 wins
                if (blackCount == 4)
                {
                    winner = "Player 2 wins!";
                }
            }
        }
        return (winner);
    }


    // ----------------------------------------------------------
    /**
     * Moves that have been made
     *
     * @return solution all of the moves made by player 1
     */
    public String playerOneMoves()
    {
        String playerOneMoves = "Player One: ";
        // Returns the solution coordinates of the solver if there is one
        while (!playerOneMovement.empty())
        {
            playerOneMoves += playerOneMovement.pop().toString();
        }
        return playerOneMoves += "";
    }


    // ----------------------------------------------------------
    /**
     * Moves that have been made
     *
     * @return solution all of the moves made by player 2
     */
    public String playerTwoMoves()
    {
        String playerTwoMoves = "Player Two: ";
        // Returns the solution coordinates of the solver if there is one
        while (!playerTwoMovement.empty())
        {
            playerTwoMoves += playerTwoMovement.pop().toString();
        }
        return playerTwoMoves += "";
    }
}
