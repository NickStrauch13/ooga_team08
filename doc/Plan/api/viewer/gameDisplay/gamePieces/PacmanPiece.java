public interface PacmanPiece {

    /**
     * Represents a PacmanPiece view object
     * @param cellSize - the size of a cell in the view Grid
     */
    PacmanPiece(int cellSize);

    /**
     * Creates a circle representing the PacmanPiece that can be positioned in the grid
     * @return
     */
    @Override
    Node makeNode();

}