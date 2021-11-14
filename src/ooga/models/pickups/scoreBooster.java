package ooga.models.pickups;

import ooga.models.PickupGame;

public class scoreBooster extends pickup{
    private static final int SCORE_TO_ADD=100;

    public scoreBooster(int row, int col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.addScore(SCORE_TO_ADD);
    }
}
