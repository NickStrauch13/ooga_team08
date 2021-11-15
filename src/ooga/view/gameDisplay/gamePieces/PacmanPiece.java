package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacmanPiece extends MovingPiece {
    private static final String CSS_ID = "pacmanPiece";

    @Override
    protected Node makeNode(){
        Circle testPacman = new Circle(12.5, Color.YELLOW);
        setIDs(testPacman, CSS_ID, getCellIndexID());
        return testPacman;
    }


}
