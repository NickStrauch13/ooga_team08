public interface AKey extends KeyViewAction {

  /**
   * Constructor for AKey command
   * @param boardView
   */
  public AKey(BoardView boardView);

  /**
   * Add one life to user.
   */
  @Override
  public void doAction();


}