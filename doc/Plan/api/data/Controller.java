public interface Controller {
    /**
     * The constructor of the game controller that starts and controls the overall communication between the frontend and backend
     * @param stage the Stage object for the view
     * @throws IOException
     * @throws ParseException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Controller(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        stage.setTitle(TITLE);
        stage.setScene(startScreen.createScene());
        stage.show();
        animationSpeed = 0.3;
    }

    /**
     * Initialize a Pacman game
     * @param path The directory of a layout file
     */
    public void initializeGame(String path);

    /*
    Initialize all game objects within the Board object
     */
    void initializeBoard(int numOfRows, int numOfCols, Map<Integer, String> gameObjectMap, List<List<String>> stringBoard);

    /*
    Initialize all pieces within the BoardView object
     */
    void initializeBoardView(int numOfRows, int numOfCols, Map<Integer, String> gameObjectMap, List<List<String>> stringBoard);

    /**
     * Get the number of lives remained
     * @return the number of lives remained
     */
    public int getLives();

    /**
     * Get the current game scores
     * @return the current game scores
     */
    public int getScore();

    /**
     * Get the game category
     * @return the game category
     */
    public String getGameType();

    /**
     * Get the BoardView object of the game
     * @return the Boardview object
     */
    public BoardView getBoardView();

    /**
     * Get the dimension of each cell
     * @return the size of a cell in the board
     */
     int getCellSize();

    /**
     * Update and sync each frame of the game with the last direction used
     * @param direction the string value for the direction
     */
     void step(String direction);

    /**
     * Access the current coordinates of the user
     * @return (x,y) of the current position
     */
    int[] getUserPosition();

    /**
     * Gets the new ghost position of the ghost identified by the given ID
     * @param nodeID
     * @return
     */
    int[] getGhostPosition(String nodeID);

    /**
     * Sends information about the collision to the backend
     * @param nodeID
     * @return
     */
    boolean handleCollision(String nodeID);

    /**
     * Resets game stats and states
     */
    void resetGame();

    /**
     * Returns the level to the frontend
     */
    int getLevel();

    /**
     * @return true if game over otherwise false
     */
    boolean isGameOver();

}
