package ooga.models.creatures.cpuControl;

public interface CPUCreature {
    int[] getCurrentDirection();

    void setCurrentDirection(int[] currentDirection);

    int getHomeX();

    int getHomeY();

    void moveTo(int newXPos, int newYPos);

    void die();
}
