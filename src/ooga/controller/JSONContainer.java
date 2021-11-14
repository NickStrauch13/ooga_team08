package ooga.controller;

import java.util.List;

public class JSONContainer {

    private List<List<Integer>> myInfo;
    private int myNumOfRows;
    private int myNumOfCols;

    /**
     * The constructor of a JSON Reader object that holds all info from the json configuration file
     * @param rows number of rows of the board
     * @param cols number of columns of the board
     * @param boardInfo A 2-d list that contains a status value for each cell
     */
    public JSONContainer(int rows, int cols, List<List<Integer>> boardInfo) {
        myNumOfRows = rows;
        myNumOfCols = cols;
        myInfo = boardInfo;
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
        return myInfo.get(0).size() == myNumOfCols;
    }

    /**
     * Access a 2-D list that contains status values for all cells on the board
     * @return the values of all cells in the board
     */
    public List<List<Integer>> getMyInfo() {
        return myInfo;
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
}
