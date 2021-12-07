public interface SKey extends KeyViewAction {

  /**
   * Constructor for SKey command
   * @param boardView
   */
  public SKey(BoardView boardView);

  /**
   * Subtract one million points from user score.
   */
  @Override
  public void doAction();


}