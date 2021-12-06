public interface GameButtons {

    /**
     * constructor for GameButtons, initializes the properties required for the proper functioning
     * of the buttons that are created in this class
     * @param stage
     * @param width
     * @param height
     * @param controller
     * @param simManager
     */
    void GameButtons(Stage stage, int width, int height, BasicController controller, SimulationManager simManager);

    /**
     * creates and styles the HBox containing the buttons to be displayed at the bottom of the GameDisplay screen
     */
    void makeButtonBox();

    /**
     * Returns the user to the homescreen by resetting the display
     */
    void goHome();

    /**
     * Changes the picture that's displayed when the user presses the play/pause button
     */
    void playPause();

    /**
     * Rests the game the displayed grid when starting a new level
     */
    void restartGame();
}