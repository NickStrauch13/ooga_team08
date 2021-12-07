public interface ExtralifePiece{
  /**
   * Represents an Extralife piece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  ExtralifePiece(int cellSize);

  /**
   * Creates a circle representing the Extralife piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}