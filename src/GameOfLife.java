/**
 * Interface for Game of Life
 * It describes the methods needed for this game.
 * <br/>
 * @see <a href="http://en.wikipedia.org/wiki/Conway's_Game_of_Life">Conway's_Game_of_Life<a/>
 */
public interface GameOfLife {

    /**
     * Goes to the next generation on the Game of Life.
     * After calling this method the state of the {#code GameOfLifeMatrixImpl} object will change.
     * Use {#method printCurrentState} to print the new state.
     */
    void nextGeneration();

    /**
     * Set a cell status, if status is 0 or below the cell will be dead otherwise alive.
     * <b>Note:</b> Indexes are from 1 to width or height(inclusive).
     *
     * @param x      - a value from 1 to height.
     * @param y      - a value from 1 to width.
     * @param status a positive value for a living cell.
     *          if the indexes are out of bounds.
     */
    void setCellStatus(int x, int y, int status);

    /**
     *  Print the current state of the Game of Life.
     */
    void printCurrentState();
}