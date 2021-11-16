public interface MovingPiece {

    /**
     * Creates a movingPiece view object
     * @param cellSize - the size of a square in the view grid
     */
     MovingPiece(int cellSize);

    /**
     * Updates the position of the MovingPiece ImageView Node object.
     * @param x x location in the Group.
     * @param y y location in the Group.
     */
    void updatePosition(double x, double y);

    /**
     * Gets the current X value of the MovingPiece
     * @return
     */
    double getX();

    /**
     * Gets the current Y value of the MovingPiece
     * @return
     */
    double getY();

}