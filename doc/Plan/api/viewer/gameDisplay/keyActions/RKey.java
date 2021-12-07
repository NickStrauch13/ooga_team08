public interface RKey extends KeyViewAction {

  /**
   * Constructor for RKey command
   * @param boardView
   */
  public RKey(BoardView boardView);

  /**
   * Resets user position.
   */
  @Override
  public void doAction();


}