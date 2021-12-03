public interface InfoContainer {

    /**
     * Check if the number of rows of the board is equal to the claimed size
     * @return Whether the row number of the board is equal to the claimed value
     */
    public boolean checkNumOfRows();

    /**
     * Check if the number of columns of the board is equal to the claimed value
     * @return Whether the column number of the board is equal to the claimed value
     */
    public boolean checkNumOfCols();

    /**
     * Access a 2-D list that contains status values for all cells on the board
     * @return the values of all cells in the board
     */
    public List<List<Integer>> getMyInfo();

    /**
     * Access the number of rows of the board
     * @return the number of rows of the board
     */
    public int getMyNumOfRows();

    /**
     * Access the number of columns of the board
     * @return the number of columns of the board
     */
    public int getMyNumOfCols();

    /**
     * Returns the hashmap containing the stationary game objects
     * @return the map of game objects
     */
    public Map<Integer, String> getMyConversionMap();

    /**
     * Returns the hashmap containing the moving game objects "creatures"
     * @return the creature map
     */
    public Map<Integer, String> getMyCreatureMap();

}