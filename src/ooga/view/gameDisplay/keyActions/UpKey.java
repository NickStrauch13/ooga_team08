package ooga.view.gameDisplay.keyActions;

import ooga.view.gameDisplay.center.BoardView;

public class UpKey extends KeyViewAction{
  private BoardView myBoardView;

  public UpKey(BoardView boardView){
    super(boardView);
    myBoardView = boardView;
  }

  @Override
  public void doAction(){
    double curAngle = myBoardView.getUserPiece().getCurrentAngle();
    myBoardView.getUserPiece().rotatePiece(THREE_QUART_ROTATION-curAngle);
  }
}
