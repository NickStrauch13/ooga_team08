package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

import java.util.Random;

public class portal extends pickup{
    private static final int SCORE_TO_ADD=100;
    private int portalToX;
    private int portalToY;

    public portal(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        Random r = new Random();
        int[] moveTo = pickupGame.getPortalLocations().get(r.nextInt(pickupGame.getPortalLocations().size()));
        pickupGame.moveCreatureToCell(moveTo);
        super.interact(pickupGame);
    }

    public void setPortalToX(int portalToX) {
        this.portalToX = portalToX;
    }
    public void setPortalToY(int portalToY) {
        this.portalToY = portalToY;
    }
}
