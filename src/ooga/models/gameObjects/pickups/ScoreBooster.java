package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class ScoreBooster extends Pickup {
    private static final int SCORE_TO_ADD=100;

    public ScoreBooster(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.addScore(SCORE_TO_ADD);
        super.interact(pickupGame);
    }
}
