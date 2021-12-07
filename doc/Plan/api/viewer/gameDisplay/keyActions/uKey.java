public interface UKey extends KeyViewAction {

  /**
   * Constructor for UKey command
   * @param boardView
   */
  public UKey(BoardView boardView);

  /**
   * Powers up pacman (makes ghosts edible).
   */
  @Override
  public void doAction();


}