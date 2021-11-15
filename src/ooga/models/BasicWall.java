package ooga.models;

public class BasicWall extends Wall{

  public BasicWall(Integer row, Integer col){
    super(row, col);
  }

  @Override
  public void interact(PickupGame pickupGame){
    //Do nothing for basic wall...
  }
}
