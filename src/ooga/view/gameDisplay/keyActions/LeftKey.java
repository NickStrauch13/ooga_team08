package ooga.view.gameDisplay.keyActions;

import ooga.controller.Controller;
import ooga.view.gameDisplay.center.BoardView;

public class LeftKey extends KeyViewAction{
  private BoardView myBoardView;

  public LeftKey(BoardView boardView, Controller controller){
    super(boardView, controller);
    myBoardView = boardView;
  }

  /**
   * Rotates the creature to LEFT (180 degrees).
   */
  @Override
  public void doAction(){
    myBoardView.getUserPiece().rotatePiece(HALF_ROTATION);
  }

}
