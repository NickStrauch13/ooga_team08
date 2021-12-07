package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class Invincibility extends Pickup {
    private static final int POWERUP_TIME = 500;

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public Invincibility(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
    public void interact(PickupGame pickupGame){
        pickupGame.getUser().setInvincible(true);
        pickupGame.setPowerupEndtime(pickupGame.getStepCounter()+POWERUP_TIME);
        super.interact(pickupGame);
    }
}