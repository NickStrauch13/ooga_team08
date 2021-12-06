package ooga.models.game;

import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;
import ooga.models.gameObjects.GameObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Board {
    ArrayList<int[]> getPortalLocations();

    void setPortalsGone();

    ArrayList<int[]> getWallLocations();

    void removePortal(int[] portalLocation);

    void createGameObject(int row, int col, String gameObjectType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    /**
     * Adds a Pacman to the board when launching the game.
     *
     * @param creatureType
     */
    void createCreature(int xPos, int yPos, String creatureType, int creatureSize) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    Map<Integer, List<Integer>> generateAdjacencies();

    /**
     * gets the current state of the cell
     *
     * @return true if it's a wall
     */
    boolean getisWallAtCell(int row, int col);

    void setWallatCell(int[] position, boolean set);

    GameObject getGameObject(int row, int col);

    int getCols();

    int getRows();

    List<CPUCreature> getMyCPUCreatures();

    UserCreature getMyUser();

    CPUCreature getMyCPU(String myID);

    int getNumPickupsAtStart();

    GameObject[][] getGameObjects();
}
