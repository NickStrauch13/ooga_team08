package ooga.models.creatures.cpuControl;

import ooga.models.creatures.Creature;

public class CPUCreature extends Creature {
    public int[] getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int[] currentDirection) {
        this.currentDirection = currentDirection;
    }

    int[] currentDirection;
    @Override
    public void moveTo(int newXPos,int newYPos) {

    }

    @Override
    public void die() {

    }
}
