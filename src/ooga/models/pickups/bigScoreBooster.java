package ooga.models.pickups;

import ooga.models.game.PickupGame;

public class bigScoreBooster extends pickup{
    private static final int SCORE_TO_ADD=200;

    public bigScoreBooster(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.addScore(SCORE_TO_ADD);
    }
}
