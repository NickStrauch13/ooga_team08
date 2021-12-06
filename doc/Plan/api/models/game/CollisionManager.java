package ooga.models.game;

public interface CollisionManager {
    /**
     * Returns a string containing the current collision info
     *
     * @return
     */
    String getCurrentCollision();

    /**
     * Sets the current collision to the ID of the thing that collided with the user
     *
     * @param objectID
     */
    void setCollision(String objectID);

    /**
     * Checks if there was or was not a collision
     *
     * @return
     */
    boolean checkIfCollision();

    /**
     * Checks if the collision involved a stationary object or creature
     *
     * @return
     */
    boolean isCreature();
}
