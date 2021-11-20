package ooga.view.gameDisplay.keyActions;

import javafx.scene.input.KeyCode;
import ooga.view.gameDisplay.center.BoardView;

public class RightKey extends KeyViewAction{
  private BoardView myBoardView;

  public RightKey(BoardView boardView){
    super(boardView);
    myBoardView = boardView;
  }

  @Override
  public void doAction(){
    double currentHeading = myBoardView.getUserPiece().getCurrentAngle();
    myBoardView.getUserPiece().rotatePiece(FULL_ROTATION - currentHeading);
  }

}
