public interface OKey extends KeyViewAction {

  /**
   * Constructor for OKey command
   * @param boardView
   */
  public OKey(BoardView boardView);

  /**
   * Add 100 points to user score.
   */
  @Override
  public void doAction();


}