public interface WinlevelPiece{
  /**
   * Represents a WinlevelPiece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  WinlevelPiece(int cellSize);

  /**
   * Creates a circle representing the WinlevelPiece piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}