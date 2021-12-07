package ooga.models.creatures.cpuControl;

import ooga.models.creatures.Creature;

public class CPUCreature extends Creature {
    int[] currentDirection;
    int homeX;
    int homeY;

    /**
     * Constructor for CPUCreatures, uses Creature class constructor and sets homeX and homeY
     * @param xPos x position for the home X of the creature
     * @param yPos y position for the homeY of the creature
     */
    public CPUCreature(Integer xPos, Integer yPos) {
        super(xPos, yPos);
        homeX = xPos;
        homeY = yPos;
    }

    /**
     * gets current movement direction
     * @return int array corresponding to direction of the creature formatted [xDirection,yDirection]
     */
    public int[] getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Set the current direction to input parameter
     * @param currentDirection set currentDirection to input parameter
     */
    public void setCurrentDirection(int[] currentDirection) {
        this.currentDirection = currentDirection;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public int getHomeX() {
        return homeX;
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public int getHomeY() {
        return homeY;
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public void moveTo(int newXPos,int newYPos) {
        setXpos(newXPos);
        setYpos(newYPos);
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public void die() {
        moveTo(homeX,homeY);
    }
}
