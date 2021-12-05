package ooga.models.gameObjects.pickups;

import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.game.PickupGame;

public class GhostSlower extends Pickup {
    private static final int POWERUP_TIME=650;

    public GhostSlower(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){

        int currentStep = pickupGame.getStepCounter();
        pickupGame.setPowerupEndtime(currentStep+POWERUP_TIME);
        pickupGame.setCPUSpeed();

        super.interact(pickupGame);
    }
}