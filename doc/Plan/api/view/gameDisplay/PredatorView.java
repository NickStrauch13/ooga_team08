public interface PredatorView {

    /**
     * Performs an ongoing ghost animation as the simulation runs. This will be called in the step function although
     * potentially this implementation would occur in the step funciton as well.
     */
    void ghostAnimation();

    /**
     * Changes the image of the ghost/predator to show that it is powered up. Lasts for the length of the power up.
     */
    void poweredUp();


    void updateEffect(Effect e);

}