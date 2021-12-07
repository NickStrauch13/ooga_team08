package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class ScoreBooster extends Pickup {
    private static final int SCORE_TO_ADD=100;

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public ScoreBooster(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
    public void interact(PickupGame pickupGame){
        pickupGame.addScore(SCORE_TO_ADD);
        super.interact(pickupGame);
    }
}
