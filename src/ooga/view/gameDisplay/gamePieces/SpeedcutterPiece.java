package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Map;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class SpeedcutterPiece extends GamePiece{
  public int dotRadius = 8;
  private Color myColor = Color.PINK;
  private static final String CSS_ID = "dotPiece";

  public SpeedcutterPiece(Integer cellSize, Map<String, String> myValues){
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
  protected Circle makeNode(){
    Circle dot = new Circle(dotRadius);
    dot.setId(getCellIndexID());
    dot.setFill(myColor);
    return dot;
  }
}
