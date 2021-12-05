package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class SpeedCutter extends pickup{
    private static final int SCORE_TO_ADD=1000;

    public SpeedCutter(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        int currentStep = pickupGame.getStepCounter();
        pickupGame.setPowerupEndtime(currentStep+150);
        pickupGame.setUserSpeed(pickupGame.getUser().getSpeed()*2);
        super.interact(pickupGame);
    }
}