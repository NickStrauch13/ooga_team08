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
  private Color myColor;
  private int dotRadius;
  private static final String CSS_ID = "dotPiece";

  public WinlevelPiece(Integer cellSize, Map<String, String> myValues){
    super(cellSize);
    dotRadius = getCellSize()/2;
    myColor = Color.LIMEGREEN;
    if (myValues != null) {
      if(myValues.containsKey("POWERUP_COLOR")){
        String rgbValues= myValues.get("POWERUP_COLOR"); //TODO PARSE OUT NEGATIVE DATA
        myColor=parseRGBs(rgbValues);
      }
      if(myValues.containsKey("POWERUP_SIZE")){
        dotRadius = Integer.parseInt(myValues.get("POWERUP_SIZE"));//TODO Make parser parse out non-integer data
      }
    }
    setMyPiece(makeNode());
  }

  public String getMyClass() {
    return this.getClass().getSimpleName();
  }

  @Override
  protected Rectangle makeNode(){
    Rectangle rect = new Rectangle(dotRadius,dotRadius);
    rect.setId(getCellIndexID());
    rect.setFill(myColor);
    return rect;
  }
}
