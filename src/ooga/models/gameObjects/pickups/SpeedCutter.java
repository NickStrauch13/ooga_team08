package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class SpeedCutter extends Pickup {
    private static final int POWERUP_TIME=5000;

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public SpeedCutter(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
    public void interact(PickupGame pickupGame){
        int currentStep = pickupGame.getStepCounter();
        pickupGame.setUserSpeed(pickupGame.getUser().getSpeed()*2);
        super.interact(pickupGame);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        pickupGame.setUserSpeed(pickupGame.getUser().getSpeed()/2);
                    }
                },
                POWERUP_TIME
        );

    }
}