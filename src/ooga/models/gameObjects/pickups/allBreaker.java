package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class allBreaker extends pickup{
    private static final int SCORE_TO_ADD=1000;

    public allBreaker(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.wallStateChange(false);
        pickupGame.setPowerupEndtime(pickupGame.getStepCounter()+150);
        super.interact(pickupGame);
    }
}