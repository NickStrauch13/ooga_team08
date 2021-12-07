package ooga.models.creatures;

public interface Creature {
    int getSize();

    String getId();

    void setId(String id);

    double getSpeed();

    int getHomeX();

    int getHomeY();

    void moveTo(int newXPos, int newYPos);

    void die();

    int getXpos();

    int getYpos();

    int getCenterX();

    int getCenterY();

    void setXpos(int xpos);

    void setYpos(int ypos);

    void setSize(int size);

    void setSpeed(double speed);

    void setImage(String image);

    Double getStandardSpeed();
}
