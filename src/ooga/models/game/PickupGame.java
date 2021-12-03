package ooga.models.game;

import ooga.models.gameObjects.GameObject;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;

import java.util.ArrayList;
import java.util.List;

public interface PickupGame {
    public UserCreature getUser();

    public GameObject getGameObject(int row, int col);

    public List<CPUCreature> getCPUs();

    public void addScore(int score);

    public void updatePickupsLeft();

    public int getStepCounter();

    public void setPowerupEndtime(int powerupEndtime);

    public void setBfsThreshold(int bfsThreshold);

    public ArrayList<int[]> getPortalLocations();

    public void moveCreatureToCell(int[] cellIndex);

    public String getGameType();

    public void nextLevel();

}
