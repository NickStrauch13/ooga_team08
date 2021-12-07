public interface TKey extends KeyViewAction {

  /**
   * Constructor for TKey command
   * @param boardView
   */
  public TKey(BoardView boardView);

  /**
   * Advnaces to the next level.
   */
  @Override
  public void doAction();


}