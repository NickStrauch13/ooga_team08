public interface PacmanDisplay {

    /**
     * This arranges the display of the application. Likely this will require several helper methods that
     * are difficult to predict.
     */
    void setupDisplay();

    /**
     * If a game over event is determined by the simulation manager then it will trigger the application to make
     * appearance changes to show the user that he/she lost the game.
     */
    void endGame();

    /**
     * Sets up the application for a new game.
     */
    void newGame();

}