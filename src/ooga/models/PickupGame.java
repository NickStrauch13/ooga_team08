package ooga.models;

import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;

import java.util.List;

public interface PickupGame {
    public UserCreature getUser();

    public GameObject getGameObject(int row, int col);

    public List<CPUCreature> getCPUs();

    public void addScore(int score);

}
