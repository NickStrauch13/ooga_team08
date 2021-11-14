package ooga.models.pickups;

import ooga.models.GameObject;
import ooga.models.PickupGame;

public abstract class pickup extends GameObject {

    public pickup(int row, int col) {
        super(row, col);
    }

    abstract public void interact(PickupGame pickupGame);
}
