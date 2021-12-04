package ooga.view.gameDisplay.keyActions.cheatKeys;

import ooga.controller.Controller;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.keyActions.KeyViewAction;

public class WKey extends KeyViewAction {
  private Controller myController;

  public WKey(BoardView boardView, Controller controller){
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
