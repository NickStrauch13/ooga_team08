public interface GameDisplay {

    /**
     * Constructs a new GameDisplay with properties that govern its appearance and game type
     * @param stage
     * @param width
     * @param height
     * @param viewMode
     * @param language
     * @param gameType
     * @param controller
     * @param boardView
     */
    void GameDisplay(Stage stage, int width, int height, String viewMode, String language,  String gameType, BasicController controller, BoardView boardView);

    /**
     * Sets the new scene which will show the actual pacman games
     * @param title The title for the stage
     */
    void setMainDisplay(String title);

    /**
     * Sets up the gameDisplay scene
     */
    void setupScene();

    /**
     * Displays the game over popup on the stage.
     */
    void showGameOverPopup();
}