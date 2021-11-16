public interface GamePiece {

    /**
     * Constructor for a superclass GamePiece that holds methods common to both stationary view gameObjects and
     * creature view objects
     * @param cellSize - size of a square cell in view grid
     */
    GamePiece(int cellSize){
        myCellSize = cellSize;
        myRow = 0;
        myCol = 0;
        myPiece = makeNode();
    }

    /**
     * Returns the node representing the specific view game piece.
     * @return Node that can be added to the grid pane in BoardView.
     */
    Node getPiece();

    /**
     * Sets the specific cell that the piece will reside. This is typically used to create the ID
     * for the piece node. (ex: a wall in cell r=4 c =11 would get the ID = "4,11")
     * @param row
     * @param col
     */
    void setCellIndex(int row, int col);

    /**
     * So that one class can be called to make a node that can be added to the grid pane for any subclass
     * @return
     */
    abstract Node makeNode();

    /**
     * Returns a string representing the row, col position of the GamePiece
     * @return
     */
    String getCellIndexID();

    /**
     * Creates IDs for a gamePiece so that the GamePiece can be referred to and styled
     * @param node
     * @param cssID
     * @param generalID
     */
    void setIDs(Node node, String cssID, String generalID);

    /**
     * Returns an int representing the size of a square in the view grid
     * @return
     */
    int getCellSize();

}