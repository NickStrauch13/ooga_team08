public interface ScoremultiplierPiece{
  /**
   * Represents a ScoremultiplierPiece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  ScoremultiplierPiece(int cellSize);

  /**
   * Creates a circle representing the ScoremultiplierPiece piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}