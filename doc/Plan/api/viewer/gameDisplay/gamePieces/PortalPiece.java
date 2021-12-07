public interface PortalPiece{
  /**
   * Represents a PortalPiece view object
   * @param cellSize - the size of a cell in the view Grid
   */
  PortalPiece(int cellSize);

  /**
   * Creates a circle representing the PortalPiece piece that can be positioned in the grid
   * @return Java fx node
   */
  @Override
  Node makeNode();
}