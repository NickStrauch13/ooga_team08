package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Map;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class ExtralifePiece extends GamePiece{
  private int dotRadius;
  private static final int DEFAULT_RAD = 8;
  private static final Color DEFAULT_COLOR = Color.RED;
  private Color myColor;
  private static final String CSS_ID = "dotPiece";

  public ExtralifePiece(Integer cellSize, Map<String, String> myValues){
    super(cellSize);
    dotRadius = DEFAULT_RAD;
    myColor = DEFAULT_COLOR;
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
