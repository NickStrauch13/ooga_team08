package ooga.models.game;

public class CollisionManager {
    private String currentCollision;
    public CollisionManager(){

    }
    public String getCurrentCollision() {
        return currentCollision;
    }


    public void setCollision(String objectID){
        currentCollision=objectID;
    }
    public boolean checkIfCollision(){
        return currentCollision!=null;
    }
    public boolean isCreature(){
        return(currentCollision.contains("CREATURE"));
    }


}
