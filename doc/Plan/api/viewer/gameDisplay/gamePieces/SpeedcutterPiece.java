public interface SpeedcutterPiece{
  /**
   * Represents a SpeedcutterPiece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  SpeedcutterPiece(int cellSize);

  /**
   * Creates a circle representing the SpeedcutterPiece piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}