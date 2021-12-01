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

    /**
     * Rotates the moving piece. Positive input angle rotates clockwise, negative--ccw. (angle in degrees)
     * @param rotationAngle angle the moving piece should turn clockwise.
     */
    void rotatePiece(double rotationAngle);

    /**
     * Checks if the current user creature is colliding with a node in the game group.
     * @param nodeList Nodes to check for collisions with.
     * @return The ID of the collided node if there is a collision, null otherwise.
     */
    String getCreatureCollision(List<MovingPiece> nodeList);

    /**
     * Checks if the current user creature is colliding with a node in the game group.
     * @param nodeList Nodes to check for collisions with.
     * @return The ID of the collided node if there is a collision, null otherwise.
     */
    String getCollision(List<Node> nodeList);
}