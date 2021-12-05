public inteface HomeScreen {

    /**
     * Creates the landing page which starts a new game given a file and shows high scores
     */
    HomeScreen(Stage stage, int width, int height, BasicController controller);

    /**
     * Creates the home screen scene.
     * @return the created scene object
     */
     Scene createScene();

    /**
     * Sets the new scene which will show the home screen.
     * @param title title for the stage.
     */
    void setMainDisplay(String title);

    /**
     * Sets up the Homescreen scene
     */
    void setupScene();

    /**
     * Adds home buttons to homescreen
     */
    Node homeButtons();

    /**
     * Sets username of player so that high score can be saved with the user's name
     */
     void setUserName(String userName);

    /**
     * Gets the file for the game
     */
     void readFile();

    /**
     * Sets up a new scene with the given attributes
     */
     void startNewGame();

    /**
     * Show high scores when button pressed
     */
     void displayHighScores();

}