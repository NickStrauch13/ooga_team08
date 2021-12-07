public interface IKey extends KeyViewAction {

  /**
   * Constructor for IKey command
   * @param boardView
   */
  public IKey(BoardView boardView);

  /**
   * Add 500 points to the user score.
   */
  @Override
  public void doAction();


}