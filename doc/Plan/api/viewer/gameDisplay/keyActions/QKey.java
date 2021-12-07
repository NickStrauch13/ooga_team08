public interface QKey extends KeyViewAction {

  /**
   * Constructor for QKey command
   * @param boardView
   */
  public QKey(BoardView boardView);

  /**
   * Freezes all ghosts in place forever.
   */
  @Override
  public void doAction();


}