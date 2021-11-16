public interface Game {
    /**
     * Subtracts a life from the number of Pacman lives which is defined in this class. (maybe controller)
     * @return new number of lives remaining
     */
    int loseLife();

    /**
     * Adds points to the score which is housed in this class.
     */
    void addScore();

    /**
     * Resets the lives and score if the user restarts the game etc.
     */
    void resetCreatureStates();

    /**
     * Increments the level.
     */
    void nextLevel();

    /**
     * Gets the score and level and returns it
     */
    void endGame();

    /**
     * updates the direction of movement of the user creature, called when key is pressed
     * @param lastDirection string direction corresponding to the last key press
     * @return new direction of movement
     */
    String setLastDirection(String lastDirection);

    /**
     * gets the user controlled creature in the game
     * @return UserCreature object in the game
     */
    UserCreature getUser();

    /**
     * gets GameObject at specific position
     * @param row row index of gameObject
     * @param col column index of gameObject
     * @return GameObject located at row,col
     */
    GameObject getGameObject(int row, int col);

    /**
     * gets the list of all CPUCreatures in the game
     * @return list containing all CPU Creatures in the game
     */
    List<CPUCreature> getCPUs();

    /**
     * step function that updates position and handles collisions at each step
     */
    void step();

    /**
     * moves all creatures to their next positions, called at each step
     */
    void moveCreatures();

    /**
     * moves all cpu creatures according to pre-determined algorithm
     */
    void moveCPUCreatures();

    /**
     * moves creatures to its new possible position if wall is not encountered
     */
    void moveToNewPossiblePosition();

    /**
     * takes in pixel value and returns position on board
     * @param pixels pixel position before translation
     * @return grid index value of given pixel position
     */
    int getCellCoordinate(double pixels);

    /**
     * checks if there are any pickups left
     * @return return true if there are 0 pickups left
     */
    boolean checkPickups();

    /**
     * checks if the user has lives left
     * @return return true if the user has 0 lives left
     */
    boolean checkLives();

    /**
     * Handles collisions that may occur at each step
     * @param cm collision manager instance to assist with colliding object
     */
    void dealWithCollion(CollisionManager cm);

    /**
     * decreases the number of pickups left on the board by 1
     */
    void updatePickupsLeft();

    /**
     * handles pickup collisions, applies rules based on pickup
     * @param cm collision manager instance to assist in handling
     */
    void creatureVsPickupCollision(CollisionManager cm);

    /**
     * handles creature collisions, applies rules based on creature/creature state
     * @param cm collision manager instance to assist in handling
     */
    void creatureVsCreatureCollision(CollisionManager cm);

    /**
     * sets new list of activeCPUcreatures
     * @param activeCPUCreatures new list to override current list of CPU creatures
     */
    void setActiveCPUCreatures(List<CPUCreatures> activeCPUCreatures);

    /**
     * creates array that controls movement based on properties file and input string
     * @param lastDirection last key pressed direction as a string
     * @return int[] corresponding to said direction
     */
    int[] generateDirectionArray(String lastDirection);

    /**
     * get Lives left
     * @return int number of lives left
     */
    int getLives();

    /**
     * get current score
     * @return int number equaling score
     */
    int getScore();

    /**
     * get level number
     * @return int number of current level
     */
    int getLevel();

    /**
     * get game board
     * @return current gameBoard
     */
    Board getBoard();

    /**
     * checks if game should be ended
     * @return true if game should be ended
     */
    boolean isGameOver();


}