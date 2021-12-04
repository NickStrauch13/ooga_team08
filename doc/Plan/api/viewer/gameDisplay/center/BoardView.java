public interface PacManDisplay {

    /**
     * Creates a boardView that adds and positions view objects on the screen
     * @param controller
     */
    BoardView(BasicController controller);

    /**
     * Initializes the displayed grid list at the start of the game
     * @param rows
     * @param cols
     */
    void makeBoard(int rows, int cols);

    /**
     * @return returns the initial board when a new game is being setup
     */
    Group getInitialBoard();

    /**
     * @return returns the user controlled Pacman
     */
    MovingPiece getPacman();

    /**
     * Adds any type of CPU creature to the game.
     * @param row CPU starting row
     * @param col CPU starting col
     * @param creatureName name of the creature
     */
    public void addCPUCreature(int row, int col, String creatureName);

    /**
     * Adds any type of user controlled creature to the game.
     * @param row user creature starting row.
     * @param col user creature starting column.
     * @param creatureName name of the user creature
     */
    public void addUserCreature(int row, int col, String creatureName)

    /**
     * Adds a stationary object to the grid using the given row col.
     * ObjectName will be used for reflection
     * @param row
     * @param col
     * @param objectName
     */
    void addBoardPiece(int row, int col, String objectName);

    /**
     * Creates the different frontend stationary pieces using reflection by process data from the controller
     * @param objectName
     */
    void pieceReflection(String objectName);

    /**
     * Getter method that will return the user controlled movingPiece instance.
     * @return movingPiece instance
     */
     MovingPiece getUserPiece();

    /**
     * Returns the ID of the node that the user is colliding with.
     * @return String representing the collision node.
     */
     String getUserCollision();

    /**
     * Removes the node specified by the backend from the boardView.
     */
     void removeNode(String nodeID);

    /**
     * Gets the list of the current view creatures.
     */
    List<MovingPiece> getCreatureList();

    /**
     * Creates the different frontend moving pieces using reflection by process data from the controller
     * @param objectName
     */
    MovingPiece creatureReflection(String creatureName);
}