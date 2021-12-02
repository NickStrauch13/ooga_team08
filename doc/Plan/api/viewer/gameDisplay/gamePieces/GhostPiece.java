public interface GhostPiece extends MovingPiece {

    /**
     * Initializes a ghost piece of a certain size given the cell size
     * @param cellSize
     */
    public GhostPiece(int cellSize);

    /**
     * Creates an object that can be added to the screen and associates it with the ghost
     * @return
     */
    @Override
    protected Node makeNode();


}
