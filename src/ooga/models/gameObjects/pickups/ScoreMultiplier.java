package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class ScoreMultiplier extends Pickup {
    private static final int MULTIPLIER=2;

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public ScoreMultiplier(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
    public void interact(PickupGame pickupGame){
        pickupGame.multiplyScore(MULTIPLIER);
        super.interact(pickupGame);
    }
}
