package ooga.controller;

import java.util.List;
import java.util.Map;

public interface JSONContainer {
    /**
     * Check if the number of rows of the board is equal to the claimed size
     *
     * @return Whether the row number of the board is equal to the claimed value
     */
    boolean checkNumOfRows();

    /**
     * Check if the number of columns of the board is equal to the claimed value
     *
     * @return Whether the column number of the board is equal to the claimed value
     */
    boolean checkNumOfCols();

    /**
     * Access a 2-D list that contains status values for all cells on the board
     *
     * @return the values of all cells in the board
     */
    List<List<Integer>> getMyInfo();

    /**
     * Access a 2-D list that contains the string value of all cells on the board
     *
     * @return the string values of all cells in the board
     */
    List<List<String>> getMyStringBoard();

    /**
     * Access the number of rows of the board
     *
     * @return the number of rows of the board
     */
    int getMyNumOfRows();

    /**
     * Access the number of columns of the board
     *
     * @return the number of columns of the board
     */
    int getMyNumOfCols();

    /**
     * Returns the hashmap containing the stationary game objects
     *
     * @return the map of game objects
     */
    Map<Integer, String> getMyConversionMap();

    /**
     * Returns the hashmap containing the moving game objects "creatures"
     *
     * @return the creature map
     */
    Map<Integer, String> getMyCreatureMap();

    GameSettings getMyGameSettings();

    boolean isMissingContent();
}
