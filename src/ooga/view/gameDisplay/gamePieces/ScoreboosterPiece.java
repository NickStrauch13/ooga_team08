package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class ScoreboosterPiece extends GamePiece{
  private int dotRadius = 4;
  private Color myColor = Color.YELLOW;
  private static final String CSS_ID = "dotPiece";

  public ScoreboosterPiece(Integer cellSize){
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
  }

  @Override
  protected Circle makeNode(){
    Circle dot = new Circle(dotRadius);
    dot.setId(getCellIndexID());
    dot.setFill(myColor);
    return dot;
  }
}
