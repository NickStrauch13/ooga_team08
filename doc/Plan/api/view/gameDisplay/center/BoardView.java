

public interface PacManDisplay {

    /**
     * Creates a boardView that adds and positions view objects on the screen
     * @param controller
     */
    BoardView(Controller controller)

    /**
     * Initializes the displayed grid list at the start of the game
     * @param rows
     * @param cols
     */
    void makeBoard(int rows, int cols);

    /**
     * @return returns the initial board when a new game is being setup
     */
    Group getInitialBoard()

    /**
     * @return returns the user controlled Pacman
     */
    MovingPiece getPacman()

    /**
     * Adds a creature to the view grid. Uses the row column to calculate the pixel location
     * ObjectName will be used for reflection
     * @param row
     * @param col
     * @param objectName
     */
    void addCreature(int row, int col, String objectName)

    /**
     * Adds a stationary object to the grid using the given row col.
     * ObjectName will be used for reflection
     * @param row
     * @param col
     * @param objectName
     */
    void addBoardPiece(int row, int col, String objectName)



}