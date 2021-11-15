package ooga.models.gameObjects.pickups;

import ooga.models.gameObjects.GameObject;
import ooga.models.game.PickupGame;

public abstract class pickup extends GameObject {

    public pickup(Integer row, Integer col) {
        super(row, col);
    }

    abstract public void interact(PickupGame pickupGame);
}
