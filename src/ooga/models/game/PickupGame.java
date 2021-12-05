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

    public ArrayList<int[]> getPortalLocations();

    public void setPortalsGone();

    public void removePortal(int[] portalLocations);

    public void moveCreatureToCell(int[] cellIndex);

    public void nextLevel();

    public void addLife();

    public void setUserSpeed(double i);

    public void wallStateChange(boolean toSet);

    public void multiplyScore(int multiplier);

    public void setCPUSpeed(double v);
}
