package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class DotPiece extends GamePiece{
  public static final int DOT_RAD = 4;
  private static final String CSS_ID = "dotPiece";

  public DotPiece(){
  }

  @Override
  protected Node makeNode(){
    Circle dot = new Circle(DOT_RAD);
    setIDs(dot, CSS_ID, getCellIndexID());
    return dot;
  }
}
