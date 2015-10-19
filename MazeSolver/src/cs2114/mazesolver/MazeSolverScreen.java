package cs2114.mazesolver;

import sofia.graphics.Color;
import sofia.app.ShapeScreen;
import android.widget.*;

// -------------------------------------------------------------------------
/**
 * Maze solver screen class for the maze solver game.
 *
 * @author Evan Shaw (evans29)
 * @version 2015.06.16
 */
public class MazeSolverScreen
    extends ShapeScreen
{
    // ~ Fields ................................................................
    private Maze          mazeS;
    /**
     * Button clicked
     */
    private ButtonState   clicked;
    private CoverTile[][] cells;
    private TextView      infoLabel;
    private float         size;


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
        mazeS = new Maze(10);
        cells = new CoverTile[10][10];
        float cellWidth = getWidth() / 10;
        float cellHeight = getHeight() / 10;
        size = Math.min(cellWidth, cellHeight);
        // Creates the board with all cells covered
        // Iterates through the board at the X coordinates
        for (int x = 0; x < 10; x++)
        {
            // Iterates through the board at the Y coordinates
            for (int y = 0; y < 10; y++)
            {
                CoverTile tile =
                    new CoverTile(
                        cellWidth * x,
                        cellHeight * y,
                        (cellWidth * x) + cellWidth,
                        (cellHeight * y) + cellHeight);
                tile.setFillColor(Color.green);
                tile.setColor(Color.darkTurquoise);
                // Sets the colors of the start location to be orange
                if (x == 0 && y == 0)
                {
                    tile.setFillColor(Color.orange);
                }
                // Sets the colors of the goal location to be magenta
                else if (x == 9 && y == 9)
                {
                    tile.setFillColor(Color.magenta);
                }
                cells[x][y] = tile;
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
        processTouch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * Called when the user moves his or her finger on the screen.
     *
     * @param x
     *            the x-coordinate where the screen was touched
     * @param y
     *            the y-coordinate where the screen was touched
     */
    public void onTouchMove(float x, float y)
    {
        processTouch(x, y);
    }


    // ----------------------------------------------------------
    /**
     * Called when the user moves his or her finger on the screen.
     *
     * @param x
     *            the x-coordinate where the screen was touched
     * @param y
     *            the y-coordinate where the screen was touched
     */
    private void processTouch(float x, float y)
    {
        int xC = (int)Math.floor(x / size);
        int yC = (int)Math.floor(y / size);
        // If the draw button is clicked, draws cells to be orange wall
        if (clicked == ButtonState.DRAW)
        {
            if (!mazeS.getCell(new Location(xC, yC)).equals(
                mazeS.getGoalLocation())
                && !mazeS.getCell(new Location(xC, yC)).equals(
                    mazeS.getStartLocation()))
            {
                cells[xC][yC].setFillColor(Color.orange);
                mazeS.setCell(new Location(xC, yC), MazeCell.WALL);
            }
        }
        // If the erase button is clicked, erases walls to go back to the green
        // unexplored cell
        else if (clicked == ButtonState.ERASE)
        {
            if (!mazeS.getCell(new Location(xC, yC)).equals(
                mazeS.getGoalLocation())
                && !mazeS.getCell(new Location(xC, yC)).equals(
                    mazeS.getStartLocation()))
            {
                cells[xC][yC].setFillColor(Color.green);
                mazeS.setCell(new Location(xC, yC), MazeCell.UNEXPLORED);
            }
        }
        // If the start button is clicked, sets the area pressed
        // to be the start location
        else if (clicked == ButtonState.START)
        {
            cells[xC][yC].setFillColor(Color.yellow);
            mazeS.setStartLocation(new Location(xC, yC));
            cells[xC][yC].setFillColor(Color.green);
            mazeS.setCell(new Location(xC, yC), MazeCell.UNEXPLORED);
        }
        // If the goal button is clicked, sets the area pressed
        // to be the goal location
        else
        {
            cells[xC][yC].setFillColor(Color.magenta);
            mazeS.setCell(new Location(xC, yC), MazeCell.UNEXPLORED);
            cells[xC][yC].setFillColor(Color.green);
            mazeS.setGoalLocation(new Location(xC, yC));
        }
    }


    // ----------------------------------------------------------
    /**
     * Tells what to do when draw clicked
     */
    public void drawWallsClicked()
    {
        clicked = ButtonState.DRAW;
    }


    // ----------------------------------------------------------
    /**
     * Tells what to do when erase walls clicked
     */
    public void eraseWallsClicked()
    {
        clicked = ButtonState.ERASE;
    }


    // ----------------------------------------------------------
    /**
     * Tells what to do when start clicked
     */
    public void setStartClicked()
    {
        clicked = ButtonState.START;
    }


    // ----------------------------------------------------------
    /**
     * Tells what to do when goal clicked
     */
    public void setGoalClicked()
    {
        clicked = ButtonState.GOAL;
    }


    // ----------------------------------------------------------
    /**
     * Tells what to do when solve clicked
     */
    public void solveClicked()
    {
        String solution = mazeS.solve();
        // Iterates through the board at the X coordinates
        for (int x = 0; x < 10; x++)
        {
            // Iterates through the board at the Y coordinates
            for (int y = 0; y < 10; y++)
            {
                Location spot = new Location(x, y);
                // Makes sure the new location is not the goal or start spot
                if (!(mazeS.getGoalLocation().equals(spot) || mazeS
                    .getStartLocation().equals(spot)))
                {
                    // Changes cell red if the path is a failed path
                    if (mazeS.getCell(new Location(x, y))
                        == MazeCell.FAILED_PATH)
                    {
                        cells[x][y].setFillColor(Color.red);
                    }
                    // Changes cell green if the path is a current path
                    else
                    {
                        cells[x][y].setFillColor(Color.green);
                    }
                }

            }
        }
        // Says that there was no solution if no solution was found
        if (solution == null)
        {
            solution = "No solution was possible.";
            infoLabel.setText(solution);
        }
        // Says the solution coordinates if solution was found
        else
        {
            infoLabel.setText(solution);
        }
    }


    // ----------------------------------------------------------
    /**
     * @return the maze that will be used in testing
     */
    public Maze getMaze()
    {
        return mazeS;
    }


    /**
     * @return Tells the info of the maze solution.
     */
    public String infoLabel()
    {
        return infoLabel.getText().toString();
    }
}
