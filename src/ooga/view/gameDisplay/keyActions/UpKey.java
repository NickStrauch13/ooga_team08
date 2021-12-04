package ooga.view.gameDisplay.keyActions;

import ooga.controller.Controller;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;

public class UpKey extends KeyViewAction{
  private BoardView myBoardView;

  public UpKey(BoardView boardView, ViewerControllerInterface controller){
    super(boardView, controller);
    myBoardView = boardView;
  }

  /**
   * Rotates the creature to UP (270 degrees).
   */
  @Override
  public void doAction(){
    myBoardView.getUserPiece().rotatePiece(THREE_QUART_ROTATION);
  }
}
