public interface Board {

    /**
     * creates the backend board that will keep have a Collection that keeps track of the Cells and what is
     * inside the cells.
     */
    void createBoard();

    /**
     * Updates necessary cells in the board in an optimized manner. Only cells around a Pacman can possibly change
     * their state. Maybe even only in the direction that Pacman is moving.
     */
    void updateBoard();

    /**
     * Adds a pickup to a location on the back-end board. The controller will indicate when to do this.
     * @param pickupType
     */
    void createPickup(String pickupType);

    /**
     * Adds a Pacman to the board when launching the game.
     * @param creatureType
     */
    void createCreature(String creatureType);

    /**
     * Updates the contents of a cell in the board.
     */
    void updateCellState();

}