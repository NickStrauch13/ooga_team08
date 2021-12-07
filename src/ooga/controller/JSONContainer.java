package ooga.controller;

import java.util.List;
import java.util.Map;

public class JSONContainer {

    private static final int FIRST_INDEX = 0;
    private List<List<Integer>> myInfo;
    private List<List<String>> myStringBoard;
    private int myNumOfRows;
    private int myNumOfCols;
    private Map<Integer, String> myConversionMap;
    private Map<Integer, String> myCreatureMap;
    private String gameType;
    private String myLanguage;

    private GameSettings mySettings;

    @Deprecated
    /**
     * @deprecated The constructor of a JSON Reader object that holds all info from the json configuration file
     * @param rows number of rows of the board
     * @param cols number of columns of the board
     * @param boardInfo A 2-d list that contains a status value for each cell
     * @param stringBoard A 2-d list that contains a string value for each cell
     */
    public JSONContainer(int rows, int cols, List<List<Integer>> boardInfo, List<List<String>> stringBoard, Map<Integer, String> conversionMap, Map<Integer, String> creatureMap, String type, String language) {
        myNumOfRows = rows;
        myNumOfCols = cols;
        myInfo = boardInfo;
        myStringBoard = stringBoard;
        myConversionMap = conversionMap;
        myCreatureMap = creatureMap;
        gameType = type;
        myLanguage = language;
    }

    /**
     * The constructor of a JSON Reader object that holds all info from the json configuration file
     * @param rows number of rows of the board
     * @param cols number of columns of the board
     * @param boardInfo A 2-d list that contains a status value for each cell
     * @param stringBoard A 2-d list that contains a string value for each cell
     */
    public JSONContainer(int rows, int cols, List<List<Integer>> boardInfo, List<List<String>> stringBoard, Map<Integer, String> conversionMap, Map<Integer, String> creatureMap, GameSettings gameSettings) {
        myNumOfRows = rows;
        myNumOfCols = cols;
        myInfo = boardInfo;
        myStringBoard = stringBoard;
        myConversionMap = conversionMap;
        myCreatureMap = creatureMap;
//        gameType = type;
//        myLanguage = language;
        mySettings = gameSettings;
        //TODO: 1. have mySettings here as part of the container
        //      2. having a setting object in parallel
    }

    /**
     * Check if the number of rows of the board is equal to the claimed size
     * @return Whether the row number of the board is equal to the claimed value
     */
    public boolean checkNumOfRows() {
        return myInfo.size() == myNumOfRows;
    }

    /**
     * Check if the number of columns of the board is equal to the claimed value
     * @return Whether the column number of the board is equal to the claimed value
     */
    public boolean checkNumOfCols() {
        return myInfo.get(FIRST_INDEX).size() == myNumOfCols;
    }

    /**
     * Access a 2-D list that contains status values for all cells on the board
     * @return the values of all cells in the board
     */
    public List<List<Integer>> getMyInfo() {
        return myInfo;
    }

    /**
     * Access a 2-D list that contains the string value of all cells on the board
     * @return the string values of all cells in the board
     */
    public List<List<String>> getMyStringBoard() {
        return myStringBoard;
    }

    /**
     * Access the number of rows of the board
     * @return the number of rows of the board
     */
    public int getMyNumOfRows() {
        return myNumOfRows;
    }

    /**
     * Access the number of columns of the board
     * @return the number of columns of the board
     */
    public int getMyNumOfCols() {
        return myNumOfCols;
    }

    /**
     * Returns the hashmap containing the stationary game objects
     * @return the map of game objects
     */
    public Map<Integer, String> getMyConversionMap() {
        return myConversionMap;
    }

    /**
     * Returns the hashmap containing the moving game objects "creatures"
     * @return the creature map
     */
    public Map<Integer, String> getMyCreatureMap() {
        return myCreatureMap;
    }

    public GameSettings getMyGameSettings() {
        return mySettings;
    }

    public boolean isMissingContent() {
        if (myInfo != null && myStringBoard != null && myConversionMap != null && myCreatureMap != null && mySettings != null && myNumOfRows != FIRST_INDEX && myNumOfCols != FIRST_INDEX) {
            return false;
        }
        return true;
    }
}
