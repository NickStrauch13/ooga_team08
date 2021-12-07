public interface InvincibilityPiece{
  /**
   * Represents a InvincibilityPiece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  InvincibilityPiece(int cellSize);

  /**
   * Creates a circle representing the InvincibilityPiece piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}