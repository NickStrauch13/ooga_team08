package ooga.models.gameObjects.pickups;

import ooga.models.gameObjects.GameObject;
import ooga.models.game.PickupGame;

public abstract class Pickup extends GameObject {

    public Pickup(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.updatePickupsLeft();
    };
}
