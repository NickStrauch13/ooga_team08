public interface DotPiece {

    /**
     * Constructor for a dotPiece object
     * @param cellSize - the size of a grid square in the view grid
     */
    DotPiece(int cellSize);

    /**
     * Creates the actual circle that represents a Pacman energy dot
     * @return
     */
    @Override
    Node makeNode();

}