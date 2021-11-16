public interface PowerUpView {

    /**
     * Adds a power up to the screen. Likely this will take coordinates so that the game knows where to add
     * the powerup.
     */
    void addPowerUp();

    /**
     * The power up will disappear when the Pacman collects it.
     */
    void disappear();

    /**
     * Most of the effect of the power up will be implemented in the model and the board view class will update
     * the grid accordingly. However if a display effect is needed then it will be defined here.
     */
    abstract void getEffect();

}