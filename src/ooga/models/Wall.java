package ooga.models;

import ooga.models.GameObject;
import ooga.models.PickupGame;

public abstract class Wall extends GameObject {
    public Wall(int row, int col) {
        super(row, col);
    }

    public abstract void interact(PickupGame pickupGame);
}
