public interface YKey extends KeyViewAction {

  /**
   * Constructor for YKey command
   * @param boardView
   */
  public YKey(BoardView boardView);

  /**
   * Lose one life.
   */
  @Override
  public void doAction();


}