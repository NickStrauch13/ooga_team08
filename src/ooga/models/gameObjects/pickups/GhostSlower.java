package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class GhostSlower extends Pickup {
    private static final int POWERUP_TIME=5000;
    private static final double MULTIPLIER = 2.0;
    private static final double DOUBLE = 0.5;
    private static final double HALF = DOUBLE;

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

        pickupGame.setCPUSpeed(MULTIPLIER);

        super.interact(pickupGame);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

                        pickupGame.setCPUSpeed(HALF);
                    }
                },
                POWERUP_TIME
        );
    }
}