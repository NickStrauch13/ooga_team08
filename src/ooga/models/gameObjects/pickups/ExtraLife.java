package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

public class ExtraLife extends pickup{
    private static final int SCORE_TO_ADD=1000;

    public ExtraLife(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        pickupGame.addLife();
        super.interact(pickupGame);
    }
}