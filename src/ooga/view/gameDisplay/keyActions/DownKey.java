package ooga.view.gameDisplay.keyActions;

import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;

public class DownKey extends KeyViewAction{
  private BoardView myBoardView;

  public DownKey(BoardView boardView, ViewerControllerInterface controller){
    super(boardView, controller);
    myBoardView = boardView;
  }

  /**
   * Rotates the creature to DOWN (90 degrees).
   */
  @Override
  public void doAction(){
    myBoardView.getUserPiece().rotatePiece(QUART_ROTATION);
  }

}