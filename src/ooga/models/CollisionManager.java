package ooga.models;

public class CollisionManager {
    public String getCurrentCollision() {
        return currentCollision;
    }

    String currentCollision;
    public void setCollision(String objectID){
        currentCollision=objectID;
    }
    public boolean checkIfCollision(){
        return currentCollision!=null;
    }
    public boolean isCreature(){
        return(currentCollision.contains("Creature"));
    }


}
