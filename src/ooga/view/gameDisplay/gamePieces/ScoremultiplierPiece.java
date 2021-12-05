package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class ScoremultiplierPiece extends GamePiece{
  private int edge = 7;
  private Color myColor=Color.GREEN;
  private static final String CSS_ID = "dotPiece";

  public ScoremultiplierPiece(Integer cellSize){
    super(cellSize);
  }

  @Override
  protected Rectangle makeNode(){
    Rectangle rect = new Rectangle(edge,edge);
    rect.setId(getCellIndexID());
    rect.setFill(myColor);
    return rect;
  }
}
