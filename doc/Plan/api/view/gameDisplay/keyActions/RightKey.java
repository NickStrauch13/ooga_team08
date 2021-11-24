public interface RightKey extends KeyViewAction {

    /**
     * Constructor for UpKey command
     * @param boardView
     */
    public RightKey(BoardView boardView);

    /**
     * Rotates the creature to the RIGHT (0 degrees).
     */
    @Override
    public void doAction();


}