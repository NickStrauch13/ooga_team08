public interface CreatureView {

    /**
     * Moves the Pacman forward on the screen based on user input.
     */
    void forward();

    /**
     * Rotates the Pacman based on user input.
     */
    void turn();

    /**
     * Makes the creature (Pacman or ghost) disappear on screen if/when necessary.
     */
    void disappear();

    /**
     * Alters how the creature is displayed if the creature has a powerup. Potentially this will be abstract because
     * it will probably require the Pacman and ghost to change the image.
     */
    abstract void poweredUp();

    /**
     * Gets the current coordinates of the creature in the game.
     * @return
     */
    double[] findBoardLocation();

    /**
     * Makes the creature return to its starting place.
     */
    void sendHome();

}