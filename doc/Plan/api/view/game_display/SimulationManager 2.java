public interface SimulationManager {

    /**
     * Runs the step function that governs how each creature's position is updated. Updates pickups, highscore,
     * score, and lives. Checks if game is won or lost.
     */
    void step();

    /**
     * Starts the simulation and resets the game to it's initial starting point. It will take a parameter currently
     * of unkown type that will determine which level to start the game at. If just starting the game or restarting
     * it will start the game at level one.
     */
    void run();

    /**
     * Starts the animation with the default speed and sets the Timeline to INDEFINITE.
     */
    void startSimulation();

    /**
     * Pauses the animation and brings up a pause screen.
     */
    void pause();

    /**
     * Restarts the game by clearing the game board, reseting nodes to their initial values, and likely calling
     * run at the end (or step).
     */
    void restart();

    /**
     * Quits out of the game and returns to the HomeScreen.java page.
     */
    void quit();

    /**
     * Stops the animation when the game is won or lost.
     */
    void stopAnimation();

    /**
     * Similar to restart so we may extract a method for the commonalities. However rather than restarting at
     * level one it will restart the game but at the next level.
     */
    void nextLevel();

    /**
     * Called in the step function. This will check if the win criteria (all dots collected) has been fulfilled.
     * @return true if game won false otherwise
     */
    boolean gameWon();

    /**
     * Checks no more lives remain. Called after a collision with a ghost.
     */
    void gameOver();

}