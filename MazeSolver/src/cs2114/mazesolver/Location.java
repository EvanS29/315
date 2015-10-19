package cs2114.mazesolver;

// -------------------------------------------------------------------------
/**
 * Represents a pair of x and y coordinates in the maze.
 *
 * @author Evan Shaw (evans29)
 * @version 2015.06.09
 */
public class Location
    implements ILocation
{
    private int xCoord;
    private int yCoord;


    // ----------------------------------------------------------
    /**
     * Create a new Location object.
     *
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public Location(int x, int y)
    {
        this.xCoord = x;
        this.yCoord = y;
    }


    /**
     * Method to give the coordinates of the location to the east
     *
     * @return the coordinates of the location moved to the east
     */
    @Override
    public Location east()
    {
        Location eastCoord = new Location(xCoord + 1, yCoord);
        return eastCoord;
    }


    /**
     * Method to give the coordinates of the location to the north
     *
     * @return the coordinates of the location moved to the north
     */
    @Override
    public Location north()
    {
        Location northCoord = new Location(xCoord, yCoord - 1);
        return northCoord;
    }


    /**
     * Method to give the coordinates of the location to the south
     *
     * @return the coordinates of the location moved to the south
     */
    @Override
    public Location south()
    {
        Location southCoord = new Location(xCoord, yCoord + 1);
        return southCoord;
    }


    /**
     * Method to give the coordinates of the location to the west
     *
     * @return the coordinates of the location moved to the west
     */
    @Override
    public Location west()
    {
        Location westCoord = new Location(xCoord - 1, yCoord);
        return westCoord;
    }


    /**
     * Method to give the x value
     *
     * @return the x coordinate
     */
    @Override
    public int x()
    {
        return xCoord;
    }


    /**
     * Method to give the y value
     *
     * @return the y coordinate
     */
    @Override
    public int y()
    {
        return yCoord;
    }


    // ----------------------------------------------------------
    /**
     * Method to override the equals method
     *
     * @param obj
     *            the object to compare
     * @return false if it the two objects are not equal in value
     */
    public boolean equals(Object obj)
    {
        //checks if objects are equal if the objects are the same type
        if (obj instanceof Location)
        {
            Location spot = (Location)obj;
            //checks if objects are equal
            if (spot.x() == this.x() && spot.y() == this.y())
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Method to override the toString method
     *
     * @return the coordinates like "(x, y)"
     */
    public String toString()
    {
        return "(" + xCoord + ", " + yCoord + ")";
    }

}
