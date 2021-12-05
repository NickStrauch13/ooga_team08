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

    public boolean isPoweredUp() {return isPoweredUp;}
    public void setPoweredUp(boolean poweredUp) {
        isPoweredUp = poweredUp;
    }

    public boolean isInvincible() {return isInvincible;}
    public void setInvincible(boolean invincibility) {
        isInvincible = invincibility;
    }

    @Override
    public void moveTo(int newXPos,int newYPos) {
        setXpos(newXPos);
        setYpos(newYPos);
    }

    @Override
    public void die() {
        resetCreaturePosition();
    }

    private void resetCreaturePosition(){
        this.setXpos(getHomeX());
        this.setYpos(getHomeY());
    }


}
