package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PacmanPiece extends MovingPiece {
    private static final String CSS_ID = "pacmanPiece";

    public PacmanPiece(int cellSize){
        super(cellSize);
    }

    @Override
    protected Node makeNode(){
        ImageView pacman = new ImageView("ooga/view/resources/viewIcons/pacmanImage.png");
        pacman.setFitWidth(getCellSize());
        pacman.setFitHeight(getCellSize());
        setIDs(pacman, CSS_ID, getCellIndexID());
        System.out.println(getCellSize());
        return pacman;
    }


}
