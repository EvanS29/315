package cs2114.mazesolver;

import java.util.Stack;

// -------------------------------------------------------------------------
/**
 * Represents the maze for the maze solver game.
 *
 * @author Evan Shaw (evans29)
 * @version 2015.06.09
 */
public class Maze
    implements IMaze
{
    private MazeCell[][] mazeC;
    private Location     begin;
    private Location     end;
    private int          size;


    // ----------------------------------------------------------
    /**
     * Create a new Maze object.
     *
     * @param size
     *            gives the number of cells on each side of the maze
     */
    public Maze(int size)
    {
        this.size = size;
        this.begin = new Location(0, 0);
        this.end = new Location(size - 1, size - 1);
        mazeC = new MazeCell[size][size];
        // Creates the board with all cells covered
        // Iterates through the board at the X coordinates
        for (int x = 0; x < this.size(); x++)
        {
            // Iterates through the board at the Y coordinates
            for (int y = 0; y < this.size(); y++)
            {
                mazeC[x][y] = MazeCell.UNEXPLORED;
            }
        }

    }


    // ----------------------------------------------------------
    /**
     * Method to get the location of a cell
     *
     * @param spot
     *            the location
     * @return the loactions of the maze
     */
    @Override
    public MazeCell getCell(ILocation spot)
    {
        // Makes sure that the cell is valid or tells if
        // it is invalid
        if (spot.x() < 0 || spot.x() >= size)
        {
            return MazeCell.INVALID_CELL;
        }
        else if (spot.y() < 0 || spot.y() >= size)
        {
            return MazeCell.INVALID_CELL;
        }
        else
        {
            return mazeC[spot.x()][spot.y()];
        }
    }


    // ----------------------------------------------------------
    /**
     * Method to get the location of the goal
     *
     * @return the ending location
     */
    @Override
    public Location getGoalLocation()
    {
        return end;
    }


    // ----------------------------------------------------------
    /**
     * Method to get the location of the start
     *
     * @return the starting location
     */
    @Override
    public Location getStartLocation()
    {
        return begin;
    }


    // ----------------------------------------------------------
    /**
     * Method to get the location of a cell
     *
     * @param loc
     *            the location
     * @param cell
     *            the type of cell it is
     */
    @Override
    public void setCell(ILocation loc, MazeCell cell)
    {
        // sets the cell of the location if it is not a wall or the beginning
        // or ending locations
        if ((loc.equals(end) || loc.equals(begin)) && cell == MazeCell.WALL)
        {
            return;
        }
        else
        {
            mazeC[loc.x()][loc.y()] = cell;
        }
    }


    // ----------------------------------------------------------
    /**
     * Method to set the location of the start
     *
     * @param start
     *            the location
     */
    @Override
    public void setStartLocation(ILocation start)
    {
        // Makes the start spot be unexplored if a wall is tried to be placed on
        // it
        if (this.getCell(start).equals(MazeCell.WALL))
        {
            this.setCell(start, MazeCell.UNEXPLORED);
        }
        begin = (Location)start;
    }


    // ----------------------------------------------------------
    /**
     * Method to set the location of the goal
     *
     * @param goal
     *            the location
     */
    @Override
    public void setGoalLocation(ILocation goal)
    {
        // Makes the goal spot be unexplored if a wall is tried to be placed on
        // it
        if (this.getCell(goal).equals(MazeCell.WALL))
        {
            this.setCell(goal, MazeCell.UNEXPLORED);
        }
        end = (Location)goal;
    }


    // ----------------------------------------------------------
    /**
     * Method to return the size
     *
     * @return the size
     */
    @Override
    public int size()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Method to solve the maze
     *
     * @return the solution to the maze
     */
    @Override
    public String solve()
    {
        Stack<Location> solver = new Stack<Location>();
        solver.push(begin);
        Location now = solver.peek();
        this.setCell(now, MazeCell.CURRENT_PATH);
        //if not starting at the end location helps to find a way to get to the
        //goal
        while (!now.equals(end))
        {
            // if unexplored cell to the east then move to the eastern cell
            if (this.getCell(now.east()) == (MazeCell.UNEXPLORED))
            {
                this.setCell(now.east(), MazeCell.CURRENT_PATH);
                solver.push(now.east());
                now = solver.peek();
            }
            // if unexplored cell to the west then move to the western cell
            else if (this.getCell(now.west()) == (MazeCell.UNEXPLORED))
            {
                this.setCell(now.west(), MazeCell.CURRENT_PATH);
                solver.push(now.west());
                now = solver.peek();
            }
            // if unexplored cell to the south then move to the southern cell
            else if (this.getCell(now.south()) == (MazeCell.UNEXPLORED))
            {
                this.setCell(now.south(), MazeCell.CURRENT_PATH);
                solver.push(now.south());
                now = solver.peek();
            }
            // if unexplored cell to the north then move to the northern cell
            else if (this.getCell(now.north()) == (MazeCell.UNEXPLORED))
            {
                this.setCell(now.north(), MazeCell.CURRENT_PATH);
                solver.push(now.north());
                now = solver.peek();
            }
            // if failed path, remove previous cell and start over from that
            // spot
            else
            {
                this.setCell(now, MazeCell.FAILED_PATH);
                solver.pop();
                if (solver.empty())
                {
                    return null;
                }
                now = solver.peek();
            }
        }
        String solution = "";
        // Returns the solution coordinates of the solver if there is one
        while (!solver.empty())
        {
            solution += solver.pop().toString();
        }
        return solution;
    }
}
