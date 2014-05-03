import exceptions.InvalidCoordinatesException;
import exceptions.InvalidSizeException;

/**
 * An implementation of GameOfLife using a matrix as a storage.
 */
public class GameOfLifeMatrixImpl implements GameOfLife{
    /**
     * Maximum width allowed.
     */
    public static final int MAX_WIDTH = 10;

    /**
     * Maximum height allowed.
     */
    public static final int MAX_HEIGHT = 10;

    private int[][] gameOfLifeMatrix;
    private int[][] gameOfLifeMatrixNextState;
    private int height;
    private int width;


    /**
     * Default constructor as private. Enforce the usage of the constructor with parameters.
     */
    private GameOfLifeMatrixImpl(){

    }

    /**
     * Constructor for GameOfLifeMatrixImpl. Use width and height in range of 1 to 10.
     *
     * @param width the width of the GameOfLife area
     * @param height the height of the GameOfLife area
     * @throws InvalidCoordinatesException in case the coordinates are not in the specified range.
     */
    public GameOfLifeMatrixImpl(int width, int height) {
        if (width <= 0 || height <= 0 || width > MAX_WIDTH || height > MAX_HEIGHT) {
            throw new InvalidSizeException();
        }

        this.width = width;
        this.height = height;

        gameOfLifeMatrix = new int[width + 2][height + 2];
        gameOfLifeMatrixNextState = new int[width + 2][height + 2];

        for (int i = 0; i < height + 2; i++) {
            for (int j = 0; j < width + 2; j++) {
                gameOfLifeMatrix[i][j] = 0;
                gameOfLifeMatrixNextState[i][j] = 0;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextGeneration() {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                gameOfLifeMatrixNextState[i][j] = gameOfLifeRule(getNumberOfAliveNeighbours(i, j), gameOfLifeMatrix[i][j]);
            }
        }

        //copy in the proper state of Game of Life the newly created state. Old state gets completly lost.
        for (int i = 1; i <= height; i++) {
            //todo: consider the usage of System.arrayCopy instead of this
            for (int j = 1; j <= width; j++) {
                gameOfLifeMatrix[i][j] = gameOfLifeMatrixNextState[i][j];
            }
        }
    }

    /**
     * Apply the Game of Life rule on a specific cell
     * @param numberOfAliveNeighbours the number of neighbour cells that are positive
     * @param currentState the current state
     * @return 0 if the next state of the current cell is dead 1 if the next state will be alive.
     */
    private int gameOfLifeRule(int numberOfAliveNeighbours, int currentState) {
        if (currentState == 0) {
            //dead cell
            if (numberOfAliveNeighbours == 3) {
                return 1;  //cell becomes alive
            } else {
                return 0;  //cell stays dead.
            }
        } else {
            //alive cell
            if (numberOfAliveNeighbours == 2 || numberOfAliveNeighbours == 3) {
                return 1; //cell keeps living
            } else {
                return 0; //cell dies of either overpopulation or underpopulation.
            }
        }
    }

    /**
     * Get the number of alive neighbours of a cell.
     *
     * @param x   - a value from 1 to height.
     * @param y   - a value from 1 to width.
     * @return the number of alive neighbours of cell that has coordinates x and y.
     */
    private int getNumberOfAliveNeighbours(int x, int y) {
        return gameOfLifeMatrix[x - 1][y] + //up neighbour
                gameOfLifeMatrix[x][y - 1] +      //left
                gameOfLifeMatrix[x][y + 1] +      //right
                gameOfLifeMatrix[x + 1][y] +   //down neighbour
                gameOfLifeMatrix[x - 1][y - 1] + //up corners
                gameOfLifeMatrix[x - 1][y + 1] +
                gameOfLifeMatrix[x + 1][y - 1] +  //down corners
                gameOfLifeMatrix[x + 1][y + 1];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellStatus(int x, int y, int status) {
        if (x < 1 || x > height || y < 1 || y > height) {
            throw new InvalidCoordinatesException();
        }

        if (status > 0) {
            gameOfLifeMatrix[x][y] = 1;
        } else {
            gameOfLifeMatrix[x][y] = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printCurrentState() {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                System.out.print(gameOfLifeMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}