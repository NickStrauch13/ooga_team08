public interface PKey extends KeyViewAction {

  /**
   * Constructor for PKey command
   * @param boardView
   */
  public PKey(BoardView boardView);

  /**
   * Add one million score to user position.
   */
  @Override
  public void doAction();


}