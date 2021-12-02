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

}