package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class StateChanger extends Pickup {
    private static final int POWERUP_TIME=150;

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public StateChanger(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
    public void interact(PickupGame pickupGame){
        int currentStep = pickupGame.getStepCounter();
        pickupGame.setPowerupEndtime(currentStep+POWERUP_TIME);
        pickupGame.getUser().setPoweredUp(true);
        super.interact(pickupGame);
    }
}
