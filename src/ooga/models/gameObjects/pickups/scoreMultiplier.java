package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;
import ooga.models.gameObjects.pickups.pickup;

public class scoreMultiplier extends pickup {
    private static final int MULTIPLIER=2;

    public scoreMultiplier(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.multiplyScore(MULTIPLIER);
        super.interact(pickupGame);
    }
}
