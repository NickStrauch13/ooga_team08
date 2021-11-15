package ooga.models.pickups;

import ooga.models.GameObject;
import ooga.models.PickupGame;

public abstract class pickup extends GameObject {

    public pickup(Integer row, Integer col) {
        super(row, col);
    }

    abstract public void interact(PickupGame pickupGame);
}
