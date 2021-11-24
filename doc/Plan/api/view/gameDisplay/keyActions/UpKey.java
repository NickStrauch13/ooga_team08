public interface UpKey extends KeyViewAction {

    /**
     * Constructor for UpKey command
     * @param boardView
     */
        public UpKey(BoardView boardView);

        /**
         * Rotates the creature to UP (270 degrees).
         */
        @Override
        public void doAction();


}