public interface SimulationManager {

    /**
     * Simulation manager controls the funcitoning and properties of the game animation
     * @param controller
     * @param boardView
     */
    void SimulationManager(Controller controller, BoardView boardView);


    /**
     * Toggles the animation. This will be the method associated with the play/pause button.
     * @return false if the animation is paused.
     */
    boolean playPause();

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