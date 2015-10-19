package cs2114.mazesolver;

import sofia.graphics.Color;
import sofia.graphics.RectangleShape;

//-------------------------------------------------------------------------
/**
* The cover tile class to cover the Kitten
*
* @author Evan Shaw (EvanS29)
* @version (2015.06.12)
*/
public class CoverTile
    extends RectangleShape
{

    // ----------------------------------------------------------
    /**
     * Create a new CoverTile object.
     *
     * @param left
     *            the left
     * @param top
     *            the top
     * @param right
     *            the right
     * @param bottom
     *            the bottom
     */
    public CoverTile(float left, float top, float right, float bottom)
    {
        super(left, top, right, bottom);
        setFillColor(Color.red);
    }

}
