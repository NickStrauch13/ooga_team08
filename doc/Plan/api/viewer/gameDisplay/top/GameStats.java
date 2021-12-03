public interface GameStats {

    /**
     * Adds the GameStat information to the GameDisplay screen
     * @param controller
     */
    void GameStats(Controller controller);


    /**
     * Returns a horizontal row containing the game stat labels and text
     * @return
     */
    HBox makeStatLabels();

    /**
     * Sets the Lives text to the new number of lives
     * @param lives
     */
    void setLivesText(int lives);

    /**
     * Sets the scoreText to the new score
     * @param score
     */
    void setScoreText(int score);

    /**
     * Sets the levelText to the new level
     * @param level
     */
    void setLevelText(int level)

}