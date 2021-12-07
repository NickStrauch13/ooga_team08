package ooga.models.gameObjects.pickups;

import ooga.models.game.PickupGame;

import java.util.Random;

public class Portal extends Pickup {

    /**
     * constructor for pickup, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public Portal(Integer row, Integer col) {
        super(row, col);
    }

    /**
     * {@inheritDoc}
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
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
