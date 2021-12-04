package ooga.view.gameDisplay.keyActions.cheatKeys;

import ooga.controller.Controller;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.keyActions.KeyViewAction;

import javax.swing.text.View;

public class EKey extends KeyViewAction {
  private ViewerControllerInterface myController;

  public EKey(BoardView boardView, ViewerControllerInterface controller){
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
