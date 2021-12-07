package ooga.models.creatures;

public abstract class Creature {

    private static final double standardSpeed=1;
    double currentSpeed=standardSpeed;
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

    /**
     * Move the creature to a new position
     * @param newXPos new x position for the creature
     * @param newYPos new y position for the creature
     */
    public abstract void moveTo(int newXPos,int newYPos);

    /**
     * Dictates what happens upon death of creature
     */
    public abstract void die();

    /**
     * Gets the center of the creature's x position
     * @return returns the center of creature x position
     */
    public int getCenterX(){return myXpos+size/2;}

    /**
     * Gets the center of the creature's y position
     * @return returns the center of creature y position
     */
    public int getCenterY(){return myYpos+size/2;}

    /**
     * Gets home x position of the creature
     * @return returns home x position of the creature
     */
    public int getHomeX() {return homeX;}

    /**
     * Gets home y position of the creature
     * @return returns home y position of the creature
     */
    public int getHomeY() {return homeY;}

    /**
     * Gets string ID of the creature
     * @return returns string ID of the creature
     */
    public String getId() {return id;}

    /**
     * gets the size of the creature (all are squares so this is width and height)
     * @return size of the creature
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the current speed that the creature is moving at
     * @return currentSpeed as a double
     */
    public double getSpeed() {
        return currentSpeed;
    }

    /**
     * gets constant standardSpeed for resetting
     * @return standardSpeed double value
     */
    public Double getStandardSpeed() {return standardSpeed;}

    /**
     * Gets x position of the creature (top left corner)
     * @return returns x position of the creature (top left corner)
     */
    public int getXpos() {return myXpos;}

    /**
     * Gets y position of the creature (top left corner)
     * @return returns y position of the creature (top left corner)
     */
    public int getYpos() {return myYpos;}

    /**
     * move to new x position
     * @param xpos desired new x position
     */
    public void setXpos(int xpos) {
        this.myXpos = xpos;
    }

    /**
     * move to new y position
     * @param ypos desired new y position
     */
    public void setYpos(int ypos) {
        this.myYpos = ypos;
    }

    /**
     * set size of the creature
     * @param size size of the creature
     */
    public void setSize(int size){
        this.size=size;
    }

    /**
     * Change speed of the creature
     * @param speed desired new speed
     */
    public void setSpeed(double speed) {
        this.currentSpeed = speed;
    }

    /**
     * Change image of the creature
     * @param image desired new image as file path
     */
    public void setImage(String image){
        this.image=image;
    }

    /**
     * sets ID of the creature
     * @param id string ID of creature
     */
    public void setId(String id) {
        this.id = id;
    }

}
