public interface Board {

    /**
     * Adds a GameObejct to the board when launching the game.
     * @param row row index where GameObject gets added
     * @param col column index where GameObject gets added
     * @param creatureType type of GameObject to be added
     */
    void createGameObject(int row, int col, String gameObjectType);

    /**
     * Adds a Creature to the board when launching the game.
     * @param row row index where creature gets added
     * @param col column index where creature gets added
     * @param creatureType type of creature to be added
     */
    void createCreature(int row, int col, String creatureType);

    /**
     * gets GameObject at specific position
     * @param row row index of gameObject
     * @param col column index of gameObject
     * @return GameObject located at row,col
     */
    GameObject getGameObject(int row, int col);

    /**
     * returns if grid position has a wall or not
     * @param row row index of cell checked for wall
     * @param col column index of cell checked for wall
     * @return boolean representing whether row,col has a wall or not
     */
    boolean getisWallAtCell(int row, int col);

    /**
     * gets the number of rows in the board
     * @return int number of rows in board
     */
    int getRows();

    /**
     * gets the number of columns in the board
     * @return int number of columns in board
     */
    int getCols();

    /**
     * gets the list of all CPUCreatures on board
     * @return list containing all CPU Creatures on the board
     */
    List<CPUCreature> getMyCPUCreatures();

    /**
     * gets the user controlled creature on the board
     * @return UserCreature object on the board
     */
    UserCreature getMyUser();

}