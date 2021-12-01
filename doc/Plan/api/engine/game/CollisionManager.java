package ooga.models.game;

public interface CollisionManager {

    public CollisionManager();

    /**
     * Returns a string containing the current collision info
     * @return
     */
    public String getCurrentCollision();

    /**
     * Sets the current collision to the ID of the thing that collided with the user
     * @param objectID
     */
    public void setCollision(String objectID);

    /**
     * Checks if there was or was not a collision
     * @return
     */
    public boolean checkIfCollision();

    /**
     * Checks if the collision involved a stationary object or creature
     * @return
     */
    public boolean isCreature();


}
