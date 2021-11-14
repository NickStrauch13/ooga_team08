package ooga.models;

public abstract class GameObject {
    private int myRow;
    private int myCol;
    private boolean isWall;
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
