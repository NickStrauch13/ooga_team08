package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * Abstract class that still extends the GamePiece class, but it is the common ancestor for moving
 * pieces like pacman and ghosts.
 */
public abstract class MovingPiece extends GamePiece{

  public MovingPiece(int cellSize){
    super(cellSize);
  }

  /**
   * Updates the position of the MovingPiece ImageView Node object.
   * @param x x location in the Group.
   * @param y y location in the Group.
   */
  public void updatePosition(int x, int y){
    ImageView creature = (ImageView) getPiece();
    creature.setX(x);
    creature.setY(y);
  }



}
