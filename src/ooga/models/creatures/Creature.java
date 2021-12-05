package ooga.models.creatures;

public abstract class Creature {



    int standardSpeed=1;
    int speed=standardSpeed;
    int myXpos;
    int myYpos;
    int homeX;
    int homeY;
    int size;
    String image;
    String id;

    public Creature(Integer xPos, Integer yPos){
        myXpos = xPos;
        myYpos = yPos;
        homeX = xPos;
        homeY = yPos;
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

    public int getCenterX(){return myXpos+size/2;}
    public int getCenterY(){return myYpos+size/2;}
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

    public int getStandardSpeed() {return standardSpeed;}
}
