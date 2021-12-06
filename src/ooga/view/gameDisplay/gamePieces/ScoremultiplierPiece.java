package ooga.view.gameDisplay.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Map;

/**
 * Class that represents the dot pickup pieces in the view board.
 */
public class ScoremultiplierPiece extends GamePiece{

  private static final int DEFAULT_RAD = 7;
  private static final Color DEFAULT_COLOR = Color.BURLYWOOD;
  public static final String POWERUP_COLOR = "POWERUP_COLOR";
  public static final String POWERUP_SIZE = "POWERUP_SIZE";
  private int edge;
  private Color myColor;
  private static final String CSS_ID = "dotPiece";

  public ScoremultiplierPiece(Integer cellSize, Map<String, String> myValues){
    super(cellSize);
    edge = DEFAULT_RAD;
    myColor = DEFAULT_COLOR;
    if (myValues != null) {
      if(myValues.containsKey(POWERUP_COLOR)){
        String rgbValues= myValues.get(POWERUP_COLOR); //TODO PARSE OUT NEGATIVE DATA
        myColor=parseRGBs(rgbValues);
      }
      if(myValues.containsKey(POWERUP_SIZE)){
        edge = Integer.parseInt(myValues.get(POWERUP_SIZE));//TODO Make parser parse out non-integer data
      }
    }
    setMyPiece(makeNode());
  }

  @Override
  protected Rectangle makeNode(){
    Rectangle rect = new Rectangle(edge,edge);
    rect.setId(getCellIndexID());
    rect.setFill(myColor);
    return rect;
  }
}
