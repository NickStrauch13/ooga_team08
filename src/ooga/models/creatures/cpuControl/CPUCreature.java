package ooga.models.creatures.cpuControl;

import ooga.models.creatures.Creature;

public class CPUCreature extends Creature {
    int[] currentDirection;

    public CPUCreature(Integer xPos, Integer yPos) {
        super(xPos, yPos);

    }

    public int[] getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int[] currentDirection) {
        this.currentDirection = currentDirection;
    }


    @Override
    public void moveTo(int newXPos,int newYPos) {
        setXpos(newXPos);
        setYpos(newYPos);
    }

    @Override
    public void die() {

    }
}
