package ooga.models.gameObjects;

import ooga.models.game.PickupGame;

public abstract class GameObject {
    protected int myRow;
    protected int myCol;
    private boolean isWall=false;
    /**
     * constructor for gameObjets, sets position on board
     * @param row row index of pickup
     * @param col column index of pickup
     */
    public GameObject(int row, int col){
        myCol=col;
        myRow=row;
    }

    /**
     * checks if object is wall
     * @return true if wall, false if not
     */
    public boolean isWall() {return isWall;}

    /**
     * sets wall state to new wall
     * @param wall boolean for new value
     */
    public void setWall(boolean wall) {
        isWall = wall;
    }

    /**
     * Dictates actions upon user colliding with this pickup
     * @param pickupGame instance of PickupGame interface that allows for updating Game variable
     */
    public abstract void interact(PickupGame pickupGame);
}
