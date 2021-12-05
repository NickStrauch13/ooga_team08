package ooga.view.gameDisplay.keyActions.cheatKeys;

import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.keyActions.KeyViewAction;

public class PKey extends KeyViewAction {
  private ViewerControllerInterface myController;

  public PKey(BoardView boardView, ViewerControllerInterface controller){
    super(boardView, controller);
    myController = controller;
  }

  /**
   * Adds one million score to the current game
   */
  @Override
  public void doAction(){
  }
}
