package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class that represents the wall pieces in the view board.
 */
public class WallPiece extends GamePiece{
  public static final int WALL_SIZE = 25;
  private static final String CSS_ID = "wallPiece";


  public WallPiece(Integer cellSize){
    super(cellSize);
  }

  @Override
  protected Rectangle makeNode(){
    Rectangle wall = new Rectangle(WALL_SIZE, WALL_SIZE);
    wall.setId(getCellIndexID());
    wall.setFill(Color.rgb(33, 33, 222));
    return wall;
  }

}
