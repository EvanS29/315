package com.example.connectfour;

/**
 *  The state of the board's pieces
 *
 *  @author Evan Shaw (EvanS29)
 *  @version 2015.06.29
 */
public enum BoardState
{

    /**
     * Spot possible to place a chip
     */
    PLAYABLE_SPOT,
    /**
     * Spot not possible to place a chip
     */
    INVALID_SPOT,
    /**
     * Spot which has been occupied by player 2
     */
    PLAYERONE_SPOT,
    /**
     * Spot which has been occupied by player 2
     */
    PLAYERTWO_SPOT;
}
