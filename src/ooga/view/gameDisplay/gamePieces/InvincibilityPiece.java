package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class InvincibilityPiece extends GamePiece{
  public int dotRadius = 7;
  public Color myColor = Color.GOLD;
  private static final String CSS_ID = "dotPiece";

  public InvincibilityPiece(Integer cellSize, Map<String, String> myValues){
    super(cellSize);
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

  @Override
  protected Rectangle makeNode(){
    Rectangle rect = new Rectangle(dotRadius,dotRadius);
    rect.setId(getCellIndexID());
    rect.setFill(myColor);
    return rect;
  }
}
