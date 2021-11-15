package ooga.models.walls;

import ooga.models.GameObject;
import ooga.models.game.PickupGame;

public abstract class Wall extends GameObject {
    public Wall(int row, int col) {
        super(row, col);
    }

    public abstract void interact(PickupGame pickupGame);
}
