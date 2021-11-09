public interface HomeScreen {

    /**
     * Called when high score button is clicked. It opens a display that shows the high scores.
     */
    void highScores();

    /**
     * Triggered when the newGame button is clicked. It uses SimulationManager methods like run to start a new Game.
     * It will also setup the gridView.
     */
    void newGame();

    /**
     * Called after text is input into a field for username. This will be what highscores are identified with.
     */
    void setName();

    /**
     * When antiPacman button is clicked it will set a property to antiPacman which will be used to create an
     * antiPacman game using reflection.
     */
    void antiPacman();

    /**
     * When mrs Pacman button is clicked it will set a property to mrs Pacman which will be used to create a mrs
     * Pacman game using reflection.
     */
    void mrsPacman();

    /**
     * When Pacman Extreme button is clicked it will set a property to Pacman Extreme which will be used to create a
     * Pacman extreme game using reflection.
     */
    void pacManExtreme();

    /**
     * Upon launching the game a file is read with the high scores and this is used to populate the high scores
     * on the HomeScreen from previous runs of the applicaiton.
     * @param amount
     */
    void updateHighScore(int amount);

    /**
     * Sets up how the home screen is formatted with the appropriate buttons. Called from Main.java.
     */
    void setupHomeScreen();

}