package ooga.models.game;

import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;

import java.util.ArrayList;
import java.util.List;

public interface GameI {
    int getStepCounter();

    UserCreature getUser();

    List<CPUCreature> getCPUs();

    void setUserSpeed(double i);

    void setCPUSpeed(double multiplier);

    void step();

    int getTime();

    void moveCreatureToCell(int[] cellIndex);

    int getCellCoordinate(double pixels);

    boolean dealWithCollision(CollisionManager cm);

    void updatePickupsLeft();

    void addLives(int numLives);

    boolean creatureVSPickupCollision(CollisionManager cm);

    void addScore(int scoreToBeAdded);

    void multiplyScore(int multiplier);

    void resetGame();

    /**
     * Increments the level.
     */
    void nextLevel();

    /**
     * Gets the score and level and returns it
     */
    void endGame();

    int getLives();

    int getScore();

    int getLevel();

    Board getMyBoard();

    boolean isGameOver();

    void setPowerupEndtime(int powerupEndtime);

    boolean setLastDirection(String lastDirection);

    ArrayList<int[]> getPortalLocations();

    ArrayList<int[]> getWallLocations();

    void setPortalsGone();

    void removePortal(int[] portalLocations);

    void addLife();

}
