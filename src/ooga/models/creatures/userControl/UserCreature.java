package ooga.models.creatures.userControl;

import ooga.models.creatures.Creature;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UserCreature extends Creature {

    public boolean isPoweredUp() {
        return isPoweredUp;
    }

    public void setPoweredUp(boolean poweredUp) {
        isPoweredUp = poweredUp;
    }

    private boolean isPoweredUp;

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
