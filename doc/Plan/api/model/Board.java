public interface Board {

    void createBoard();

    void createPickup(String pickupType);

    void createCreature(String creatureType);

    void updateCellState();

}