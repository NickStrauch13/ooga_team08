package ooga.models.pickups;

import ooga.models.PickupGame;

public class stateChanger extends pickup{
    private static final int SCORE_TO_ADD=100;

    public stateChanger(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.getUser().setPoweredUp(true);
        pickupGame.addScore(SCORE_TO_ADD);
    }
}
