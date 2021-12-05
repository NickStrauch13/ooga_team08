package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class stateChanger extends pickup{
    private static final int POWERUP_TIME=150;

    public stateChanger(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        int currentStep = pickupGame.getStepCounter();
        pickupGame.setBfsThreshold(4);
        pickupGame.setPowerupEndtime(currentStep+POWERUP_TIME);
        pickupGame.getUser().setPoweredUp(true);
        super.interact(pickupGame);
    }
}
