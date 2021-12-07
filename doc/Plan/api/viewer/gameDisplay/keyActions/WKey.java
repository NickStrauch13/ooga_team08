public interface WKey extends KeyViewAction {

  /**
   * Constructor for WKey command
   * @param boardView
   */
  public WKey(BoardView boardView);

  /**
   * Resets all ghosts positions.
   */
  @Override
  public void doAction();


}