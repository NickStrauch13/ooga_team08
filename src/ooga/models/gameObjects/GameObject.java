package ooga.models.gameObjects;

import ooga.models.game.PickupGame;

public abstract class GameObject {
    protected int myRow;
    protected int myCol;
    private boolean isWall=false;

    public GameObject(int row, int col){
        myCol=col;
        myRow=row;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public abstract void interact(PickupGame pickupGame);

}
