public interface Pacman extends Creature{

    /**
     * Updates the double that represents the Pacman's position on the screen.
     */
    void move();

    /**
     * Updates the state of Pacman to dead.
     */
    void die();

    /**
     * Calls powerUp() if a powerup is eaten. Updates score if an energy dot is eaten.
     * @param p
     */
    void eat(pickup p);

    /**
     * Gets state alive, dead, powered up etc.
     */
    void getState();

    /**
     * Sets state to power up and gets effect of power up and applies it. Increase movement speed etc.
     */
    void powerUp();

}