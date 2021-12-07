public interface GhostslowerPiece{
  /**
   * Represents a GhostslowerPiece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  GhostslowerPiece(int cellSize);

  /**
   * Creates a circle representing the GhostslowerPiece piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}