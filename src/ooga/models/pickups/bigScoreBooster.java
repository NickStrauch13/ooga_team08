package ooga.models.pickups;

import ooga.models.creatures.userControl.UserCreature;

public class bigScoreBooster extends pickup{
    private static final int SCORE_TO_ADD=200;
    public int pickUp(UserCreature userControlled){
        return SCORE_TO_ADD;
    }
}
