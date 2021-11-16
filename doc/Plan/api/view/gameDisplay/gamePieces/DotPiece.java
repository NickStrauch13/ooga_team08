public interface DotPiece {

    /**
     * Class that represents the dot pickup pieces in the view board.
     */
    public class DotPiece extends GamePiece{
        public static final int DOT_RAD = 4;
        private static final String CSS_ID = "dotPiece";

        /**
         * Constructor for a dotPiece object
         * @param cellSize - the size of a grid square in the view grid
         */
        DotPiece(int cellSize);

        /**
         * Creates the actual circle that represents a Pacman energy dot
         * @return
         */
        @Override
        Node makeNode();
    }


}