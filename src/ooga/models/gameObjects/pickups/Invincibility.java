package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class Invincibility extends Pickup {
    private static final int POWERUP_TIME = 500;

    public Invincibility(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.getUser().setInvincible(true);

        pickupGame.setPowerupEndtime(pickupGame.getStepCounter()+POWERUP_TIME);
        super.interact(pickupGame);
    }
}