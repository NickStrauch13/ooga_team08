package ooga.models.game;

import ooga.models.gameObjects.GameObject;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;

import java.util.ArrayList;
import java.util.List;

public interface PickupGame {
    /**
     * gets user controlled object in the game
     * @return user controlled object in the game
     */
    UserCreature getUser();

    /**
     * Gets game object out at certain position
     * @param row row index of desired game object
     * @param col col index of desired game object
     * @return game object at row,col position
     */
    GameObject getGameObject(int row, int col);

    /**
     * Gets list of all CPU creatures in the game
     * @return list of all CPU creatures objects
     */
    List<CPUCreature> getCPUs();

    /**
     * Adds points to the score which is housed in this class.
     * @param score score to be added
     */
    void addScore(int score);

    /**
     * decreases the number of pickups left by 1
     */
    void updatePickupsLeft();

    /**
     * gets the number of times step has been run
     * @return number of times step has been run
     */
    int getStepCounter();

    /**
     * sets the end time for a powerup
     * @param powerupEndtime
     */
    void setPowerupEndtime(int powerupEndtime);

    /**
     * gets remaining portal locations on current level
     * @return remaining portal locations on current level
     */
    ArrayList<int[]> getPortalLocations();

    /**
     * removes all portals from board
     */
    void setPortalsGone();

    /**
     * removes one portal from board
     * @param portalLocation location of portal to be removed
     */
    void removePortal(int[] portalLocation);

    /**
     * moves creature to a certain cell on board
     * @param cellIndex index of cell (equal to row index*numCols+ col index)
     */
    void moveCreatureToCell(int[] cellIndex);

    /**
     * Increments the level.
     */
    void nextLevel();

    /**
     * adds one life
     */
    void addLife();

    /**
     * Sets new speed for the user controlled creature
     * @param newSpeed desired new speed of user controlled creature
     */
    void setUserSpeed(double newSpeed);

    /**
     * Multiply score by certain variable
     * @param multiplier value to multiply score by
     */
    void multiplyScore(int multiplier);

    /**
     * alters CPU speed by inputted multiplier
     * @param multiplier value to multiply current CPU speed by
     */
    void setCPUSpeed(double multiplier);
}
