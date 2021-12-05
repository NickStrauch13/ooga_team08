package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class WallbreakerPiece extends GamePiece{
  private int dotRadius = 5;
  private Color myColor = Color.BLACK;
  private static final String CSS_ID = "dotPiece";

  public WallbreakerPiece(Integer cellSize, Map<String, String> myValues){
    super(cellSize);
  }

  @Override
  protected Circle makeNode(){
    Circle dot = new Circle(dotRadius);
    dot.setId(getCellIndexID());
    dot.setFill(Color.rgb(200,100, 0));
    return dot;
  }
}
