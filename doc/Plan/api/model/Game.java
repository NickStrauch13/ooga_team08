public interface Game {

    /**
     * Subtracts a life from the number of Pacman lives which is defined in this class. (maybe controller)
     */
    void loseLife();

    /**
     * Adds points to the score which is housed in this class.
     */
    void addScore();

    /**
     * Resets the lives and score if the user restarts the game etc.
     */
    void resetCreatureStates();

    /**
     * Increments the level.
     */
    void nextLevel();

    /**
     * Gets the score and level and returns it
     */
    void endGame();
}