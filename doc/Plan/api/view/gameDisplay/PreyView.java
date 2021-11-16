public interface PreyView {

    /**
     * Runs the mouth animation for the Pacman/prey.
     */
    void mouthAnimation();

    /**
     * Changes the image of the Pacman (just the color of the image) to show that the Pacman is powered up.
     */
    void poweredUp();

    void updateEffect(Effect e);

}