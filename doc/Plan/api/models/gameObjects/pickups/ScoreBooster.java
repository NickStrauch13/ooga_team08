public interface ScoreBooster extends Pickup {

    /**
     * handles interaction with user controlled object, called when intersecting with user
     */
    void interact(PickupGame pickupGame);

}