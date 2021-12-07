package ooga.models.gameObjects.walls;

import ooga.models.game.PickupGame;

public class BasicWall extends Wall {

  /**
   * constructor for basicWall, sets position on board
   * @param row row index of pickup
   * @param col column index of pickup
   */
  public BasicWall(Integer row, Integer col){
    super(row, col);
  }

  /**
   * Dictates actions upon user colliding with this pickup (NONE FOR WALL)
   * @param pickupGame instance of PickupGame interface that allows for updating Game variable
   */
  @Override
  public void interact(PickupGame pickupGame){
    //Do nothing for basic wall...
  }
}
