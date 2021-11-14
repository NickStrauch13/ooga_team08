package ooga.models.creatures;

public abstract class Creature {

    int speed;
    int myXpos;
    int myYpos;
    int homeX;
    int homeY;
    int size;
    String image;
    String id;

    public Creature(int xPos, int yPos){
        myXpos = xPos;
        myYpos = yPos;
    }

    public int getSize() {
        return size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHomeX() {
        return homeX;
    }

    public int getHomeY() {
        return homeY;
    }

    public abstract void moveTo(int newXPos,int newYPos);
    public abstract void die();
    public int getXpos() {
       return myXpos;
    }

    public int getYpos() {
        return myYpos;
    }
    public void setXpos(int xpos) {
        this.myXpos = xpos;
    }

    public void setYpos(int ypos) {
        this.myYpos = ypos;
    }

    public void setSize(int size){
        this.size=size;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setImage(String image){
        this.image=image;
    }
}
