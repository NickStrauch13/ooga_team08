public interface LeftKey extends KeyViewAction {

    /**
     * Constructor for LeftKey command
     * @param boardView
     */
    public LeftKey(BoardView boardView);

    /**
     * Rotates the creature to the Left (180 degrees).
     */
    @Override
    public void doAction();


}