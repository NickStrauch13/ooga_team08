package ooga.view.gameDisplay.keyActions;

import ooga.view.gameDisplay.center.BoardView;

public class RightKey extends KeyViewAction{
  private BoardView myBoardView;

  public RightKey(BoardView boardView){
    super(boardView);
    myBoardView = boardView;
  }

  /**
   * Rotates the creature to RIGHT (0 degrees).
   */
  @Override
  public void doAction(){
    myBoardView.getUserPiece().rotatePiece(0);
  }

}
