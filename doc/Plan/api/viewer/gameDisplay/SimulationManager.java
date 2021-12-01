public interface SimulationManager {

    /**
     * Simulation manager controls the funcitoning and properties of the game animation
     * @param controller
     * @param boardView
     */
    void SimulationManager(Controller controller, GameStats gameStats, BoardView boardView);

    /**
     * Sets up the game animation (speed, lifetime, keyframes etc.)
     */
    void setupAnimation();

    /**
     * Iteratres through the frontend moving pieces (pacman and ghosts) and updates the
     * frontend using backend changes
     */
    void updateMovingPiecePositions();

    /**
     * Toggles the animation. This will be the method associated with the play/pause button.
     * @return false if the animation is paused.
     */
    boolean playPause();

    /**
     * Called in the step function it updates the stats to reflect increased points or lossed lives
     */
    void updateStats();

    /**
     * Runs the step function that governs how each creature's position is updated. Updates pickups, highscore,
     * score, and lives. Checks if game is won or lost.
     */
    void step();

    /**
     * Governs what happens when a key is pressed
     * @param code
     */
     void handleKeyInput(KeyCode code);

}