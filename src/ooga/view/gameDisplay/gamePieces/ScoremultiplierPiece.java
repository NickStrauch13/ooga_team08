package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class ScoremultiplierPiece extends GamePiece{
  public static final int edge = 7;
  private static final String CSS_ID = "dotPiece";

  public ScoremultiplierPiece(Integer cellSize){
    super(cellSize);
  }

  @Override
  protected Rectangle makeNode(){
    Rectangle rect = new Rectangle(edge,edge);
    rect.setId(getCellIndexID());
    rect.setFill(Color.rgb(184,134, 11));
    return rect;
  }
}
