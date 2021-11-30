package ooga.models.creatures.cpuControl;

import ooga.models.creatures.Creature;

public class CPUCreature extends Creature {
    int[] currentDirection;

    int homeX;
    int homeY;
    public CPUCreature(Integer xPos, Integer yPos) {
        super(xPos, yPos);
        homeX = xPos;
        homeY = yPos;
    }

    public int[] getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int[] currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public int getHomeX() {
        return homeX;
    }

    @Override
    public int getHomeY() {
        return homeY;
    }
    @Override
    public void moveTo(int newXPos,int newYPos) {
        setXpos(newXPos);
        setYpos(newYPos);
    }

    @Override
    public void die() {
        moveTo(homeX,homeY);
    }
}
