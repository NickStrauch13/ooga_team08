package ooga.models.creatures.userControl;

public interface UserCreature {
    boolean isPoweredUp();

    void setPoweredUp(boolean poweredUp);

    boolean isInvincible();

    void setInvincible(boolean invincibility);

    void moveTo(int newXPos, int newYPos);

    void die();
}
