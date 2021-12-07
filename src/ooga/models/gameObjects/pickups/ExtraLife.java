package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class ExtraLife extends Pickup {

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public ExtraLife(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     */
    public void interact(PickupGame pickupGame){
        pickupGame.addLife();
        super.interact(pickupGame);
    }
}