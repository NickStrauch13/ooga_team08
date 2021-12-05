package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class SpeedCutter extends Pickup {
    private static final int POWERUP_TIME=5000;

    public SpeedCutter(Integer row, Integer col) {
        super(row, col);
    }

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