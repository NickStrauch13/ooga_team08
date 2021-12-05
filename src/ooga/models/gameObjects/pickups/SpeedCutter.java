package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class SpeedCutter extends Pickup {
    private static final int POWERUP_TIME=150;

    public SpeedCutter(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        int currentStep = pickupGame.getStepCounter();
        pickupGame.setPowerupEndtime(currentStep+POWERUP_TIME);
        pickupGame.setUserSpeed(pickupGame.getUser().getSpeed()*2);
        super.interact(pickupGame);
    }
}