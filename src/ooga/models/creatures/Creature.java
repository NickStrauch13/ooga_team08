package ooga.models.creatures;

public abstract class Creature {
    public int getSize() {
        return size;
    }

    int size;
    String image;

    public int getSpeed() {
        return speed;
    }

    int speed;
    int xpos;
    int ypos;
    

    public int getHomeX() {
        return homeX;
    }

    public int getHomeY() {
        return homeY;
    }

    int homeX;
    int homeY;

    public abstract void moveTo(int newXPos,int newYPos);
    public abstract void die();
    public int getXpos() {
       return xpos;
    }

    public int getYpos() {
        return ypos;
    }
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
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
