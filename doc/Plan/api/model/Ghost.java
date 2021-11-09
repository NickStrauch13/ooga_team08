public interface Ghost extends Creature {

    /**
     * Moves the Ghost by keeping track of its position using a double that corresponds to its position on screen.
     * @return the new coordinates of the ghost
     */
    double[] move(double distance);

    /**
     * This method would increment the angle the ghost is facing by the amount given by degrees.
     * @param degrees
     * @return the new angle the ghost is facing
     */
    double rotate(double degrees);

    /**
     * Updates the state of the ghost to dead.
     */
    void die();

    /**
     * Determines what the next move of the ghost is.
     */
    void chase();

    /**
     * If the ghost is controlled by user in antiPacman then this defines its next move away from the user.
     */
    void runAway();

}