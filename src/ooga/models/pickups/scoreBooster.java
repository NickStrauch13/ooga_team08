package ooga.models.pickups;

import ooga.models.creatures.userControl.UserCreature;

public class scoreBooster extends pickup{
    private static final int SCORE_TO_ADD=100;
    public int pickUp(UserCreature userCreature){
        return SCORE_TO_ADD;
    }
}
