package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

import java.util.Random;

public class Portal extends Pickup {
    private static final int SCORE_TO_ADD=100;
    private int portalToX;
    private int portalToY;

    public Portal(Integer row, Integer col) {
        super(row, col);
    }

    public void interact(PickupGame pickupGame){
        Random r = new Random();
        if (pickupGame.getPortalLocations()!=null){
            int[] currentPortal = new int[] {this.myRow,this.myCol};
            pickupGame.removePortal(currentPortal);
            System.out.println(pickupGame.getPortalLocations().size());
            int[] moveTo = pickupGame.getPortalLocations().get(r.nextInt(pickupGame.getPortalLocations().size()));
            pickupGame.moveCreatureToCell(moveTo);
            pickupGame.setPortalsGone();
        }
        super.interact(pickupGame);
    }
}
