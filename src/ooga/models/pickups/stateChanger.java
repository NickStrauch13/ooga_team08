package ooga.models.pickups;

import ooga.models.creatures.userControl.UserCreature;

public class stateChanger extends pickup{
    private static final int SCORE_TO_ADD=100;
    public int pickUp(UserCreature userCreature){
        userCreature.setPoweredUp(true);
        return SCORE_TO_ADD;
    }
}
