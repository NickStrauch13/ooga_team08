package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
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
  protected Node makeNode(){
    Rectangle wall = new Rectangle(WALL_SIZE, WALL_SIZE);
    setIDs(wall, CSS_ID, getCellIndexID());
    return wall;
  }

}
