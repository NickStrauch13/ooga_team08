public interface Game {

    void run();

    void setUpBoard();

    void createCreature(String creatureType);

    void createPickup(String pickupType);

    void step();

    void loseLife();

    void addScore();

    void resetCreatures();

    void nextLevel();

    void endGame();
}