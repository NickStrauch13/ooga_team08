public interface PacManDisplay {

    /**
     * This will set up the grid on the screen. It will get input from the controller regarding the initial state
     * of the Pacman grid. It will likely use helper methods such as addDots(), buildWalls(), addGhosts()
     * that make the code more readable.
     */
    void drawGrid();

    /**
     *This will update the displayed score text. It will get changes in the score from the controller based on
     * what happens in the game.
     */
    void updateScore();

    /**
     * Update high score only updates the highscore if the current score is greater than the highscore. If so,
     * then the high score and current score will be the same and both update on the screen.
     */
    void updateHighScore();

    /**
     * Update lives will update the lives text based on collisions with the ghosts during the game.
     */
    void updateLives();

    /**
     * Removes the dots from the screen as the PacMan eats them.
     */
    void removeDot();

    /**
     * Update grid will utilize the above helper methods to update the grid. It will be given information from the
     * controller only about the nodes that changed (not the whole grid).
     */
    void updateGrid();

}