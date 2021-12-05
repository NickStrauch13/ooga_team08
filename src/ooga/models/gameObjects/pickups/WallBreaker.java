package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class WallBreaker extends Pickup {
    private static final int SCORE_TO_ADD=1000;

    public WallBreaker(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.wallStateChange(false);
        super.interact(pickupGame);
    }
}