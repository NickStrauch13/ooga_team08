package ooga.models.creatures.userControl;

import ooga.models.creatures.Creature;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UserCreature extends Creature {

    public UserCreature(Integer xPos, Integer yPos) {
        super(xPos, yPos);
    }

    private boolean isPoweredUp;
    private boolean isInvincible;

    /**
     * returns boolean referencing current state of UserCreature
     * @return true if poweredup, false if not
     */
    public boolean isPoweredUp() {return isPoweredUp;}

    /**
     * sets state of UserCreature
     * @param poweredUp new value for isPowered boolean
     */
    public void setPoweredUp(boolean poweredUp) {
        isPoweredUp = poweredUp;
    }

    /**
     * returns boolean referencing current state of UserCreature
     * @return true if isInvincible, false if not
     */
    public boolean isInvincible() {return isInvincible;}
    /**
     * sets state of UserCreature
     * @param invincibility new value for isInvincible boolean
     */
    public void setInvincible(boolean invincibility) {
        isInvincible = invincibility;
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
     * {@inheritDoc}
     */
    @Override
    public void die() {
        resetCreaturePosition();
    }

    private void resetCreaturePosition(){
        this.setXpos(getHomeX());
        this.setYpos(getHomeY());
    }


}
