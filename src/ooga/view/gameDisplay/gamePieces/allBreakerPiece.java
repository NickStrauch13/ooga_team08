package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class allBreakerPiece extends GamePiece{
  public static final int DOT_RAD = 5;
  private static final String CSS_ID = "dotPiece";

  public allBreakerPiece(Integer cellSize){
    super(cellSize);
  }

  @Override
  protected Circle makeNode(){
    Circle dot = new Circle(DOT_RAD);
    dot.setId(getCellIndexID());
    dot.setFill(Color.rgb(200,100, 0));
    return dot;
  }
}
