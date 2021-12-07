package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class GhostSlower extends Pickup {
    private static final int POWERUP_TIME=5000;

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public GhostSlower(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     */
    public void interact(PickupGame pickupGame){

        pickupGame.setCPUSpeed(2.0);

        super.interact(pickupGame);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        pickupGame.setCPUSpeed(0.5);
                    }
                },
                POWERUP_TIME
        );
    }
}