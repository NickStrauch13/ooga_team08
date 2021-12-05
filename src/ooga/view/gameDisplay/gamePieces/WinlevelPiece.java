package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Map;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class WinlevelPiece extends GamePiece{
  public static final int edge = 25;
  private static final String CSS_ID = "dotPiece";

  public WinlevelPiece(Integer cellSize, Map<String, String> myValues){
    super(cellSize);
  }

  @Override
  protected Rectangle makeNode(){
    Rectangle rect = new Rectangle(edge,edge);
    rect.setId(getCellIndexID());
    rect.setFill(Color.rgb(200,100, 0));
    return rect;
  }
}
