package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class ScoreMultiplier extends Pickup {
    private static final int MULTIPLIER=2;

    public ScoreMultiplier(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.multiplyScore(MULTIPLIER);
        super.interact(pickupGame);
    }
}
