package ooga.models.game;

public class CollisionManager {
    private static final String COMMA = ",";
    private String currentCollision;

    /**
     * Default constructor
     */
    public CollisionManager(){}

    /**
     * Returns a string containing the current collision info
     * @return
     */
    public String getCurrentCollision() {
        return currentCollision;
    }

    /**
     * Sets the current collision to the ID of the thing that collided with the user
     * @param objectID
     */
    public void setCollision(String objectID){
        currentCollision=objectID;
    }

    /**
     * Checks if there was or was not a collision
     * @return
     */
    public boolean checkIfCollision(){
        return currentCollision!=null;
    }

    /**
     * Checks if the collision involved a stationary object or creature
     * @return
     */
    public boolean isCreature(){
        return(!currentCollision.contains(COMMA));
    }


}
