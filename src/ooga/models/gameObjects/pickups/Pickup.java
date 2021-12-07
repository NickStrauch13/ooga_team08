package ooga.models.gameObjects.pickups;

import ooga.models.gameObjects.GameObject;
import ooga.models.game.PickupGame;

public abstract class Pickup extends GameObject {

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public Pickup(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * Dictates actions upon user colliding with this pickup
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
    public void interact(PickupGame pickupGame){
        pickupGame.updatePickupsLeft();
    };
}
