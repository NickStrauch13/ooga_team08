package ooga.controller;

import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;

import java.util.List;
import java.util.Map;

public interface Controller {
    /**
     * Initialize a Pacman game
     *
     * @param path The directory of a layout file
     */
    void initializeGame(String path);

    Map<Integer, String> createGameObjectMap();

    Map<Integer, String> createCreatureMap();

    int getCellCoordinate(double pixels);

    /**
     * Get the number of lives remained
     *
     * @return the number of lives remained
     */
    int getLives();

    Game getGame();

    /**
     * Get the current game scores
     *
     * @return the current game scores
     */
    int getScore();

    boolean getIsPoweredUp();

    boolean getIsInvincible();

    /**
     * Get the BoardView object of the game
     *
     * @return the Boardview object
     */
    BoardView getBoardView();

    /**
     * Get the dimension of each cell
     *
     * @return the size of a cell in the board
     */
    int getCellSize();

    /**
     * Update and sync each frame of the game with the last direction used
     *
     * @param direction the string value for the direction
     */
    void step(String direction);

    /**
     * Access the current coordinates of the user
     *
     * @return (x, y) of the current position
     */
    int[] getUserPosition();

    /**
     * Gets the new ghost position of the ghost identified by the given ID
     *
     * @param nodeID
     * @return
     */
    int[] getGhostPosition(String nodeID);

    /**
     * METHOD ONLY FOR TESTFX TESTS. Needed some way to load in a file into the file chooser.
     */
    void changeToGameScreen(String filePath);

    /**
     * Sends information about the collision to the backend
     *
     * @param nodeID
     * @return
     */
    boolean handleCollision(String nodeID);

    void loadNextLevel(BoardView boardView);

    /**
     * Receive the backend's command to reset the entire game
     */
    void restartGame();

    /**
     * Adds a new Username:Score combo to the high score CSV file
     *
     * @param nameAndScore String array where the first element is the name and the second element is the score
     */
    void addScoreToCSV(String[] nameAndScore);

    /**
     * Read high score CSV and get the top ten scores.
     *
     * @return List of string arrays where each String array is a single username:score combo.
     */
    List<String[]> getScoreData();

    /**
     * Returns the top score for the given username.
     *
     * @return String value representing the integer score.
     */
    String getTopScoreForUser();

    int getLevel();

    boolean isGameOver();

    /**
     * Returns the language of the game. If the user has input a language from the dropdown, it has priority.
     *
     * @return String representing language name.
     */
    String getLanguage();

    /**
     * Sets the UI input language for the game.
     *
     * @param lang String representing the language name.
     */
    void setUILanguage(String lang);

    /**
     * Sets the username string for the game.
     *
     * @param username String inputted by user on the home screen. Defaults to "Guest"
     */
    void setUsername(String username);

    /**
     * Returns the username for the current game.
     *
     * @return String representing username
     */
    String getUsername();

    /**
     * Returns the CSS file being used. Priority returns the UI choice box selection css mode over data file css
     *
     * @return the CSS file path as a string.
     */
    String getViewMode();

    /**
     * Sets the CSS file being used.
     *
     * @param cssName CSS file name WITHOUT .css on the end.
     */
    void setViewMode(String cssName);

    /**
     * Returns the current time of the game.
     *
     * @return Integer values representing time.
     */
    int getGameTime();

    void addOneMillionPoints();

    void addOneHundredPoints();

    void addFiveHundredPoints();

    void resetGhosts();

    void addLife();

    void goToNextLevel();

    void powerUp();

    void FreezeGhosts();

    void RemoveOneMillionPoints();

    void resetUserPosition();

    void loseLife();

    void gameOver();

    int getTimer();

    String getGameType();

    void setCellSize(int newSize);
}
