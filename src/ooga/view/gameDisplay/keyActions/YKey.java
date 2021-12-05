package ooga.view.gameDisplay.keyActions;

import ooga.controller.CheatController;
import ooga.controller.CheatControllerInterface;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.keyActions.KeyViewAction;

public class YKey extends KeyViewAction {
  private ViewerControllerInterface myController;

  public YKey(BoardView boardView, ViewerControllerInterface controller){
    super(boardView, controller);
    myController = controller;
  }

  /**
   * Adds one million score to the current game
   */
  @Override
  public void doAction(){
    myController.loseLife();
  }
}
