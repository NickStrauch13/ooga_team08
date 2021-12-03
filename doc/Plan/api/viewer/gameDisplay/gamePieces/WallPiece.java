public interface WallPiece {

    /**
     * Creates a WallPiece view object representing a pacman grid wall
     * @param cellSize
     */
    WallPiece(int cellSize);

    /**
     * Creates a square shape that can be added to the view grid
     * @return
     */
    @Override
    Node makeNode();

}