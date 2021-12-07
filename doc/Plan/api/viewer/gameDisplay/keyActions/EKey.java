public interface EKey extends KeyViewAction {

  /**
   * Constructor for EKey command
   * @param boardView
   */
  public EKey(BoardView boardView);

  /**
   * Ends the game.
   */
  @Override
  public void doAction();


}