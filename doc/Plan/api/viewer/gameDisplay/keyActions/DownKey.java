public interface DownKey extends KeyViewAction {

    /**
     * Constructor for DownKey command
     * @param boardView
     */
    public DownKey(BoardView boardView);

    /**
     * Rotates the creature to the DOWN (90 degrees).
     */
    @Override
    public void doAction();


}