public interface ScoreboosterPiece{
  /**
   * Represents a ScoreboosterPiece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  ScoreboosterPiece(int cellSize);

  /**
   * Creates a circle representing the ScoreboosterPiece piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}