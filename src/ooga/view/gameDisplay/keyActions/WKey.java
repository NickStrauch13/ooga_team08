package ooga.view.gameDisplay.keyActions;

import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;

public class WKey extends KeyViewAction {
  private ViewerControllerInterface myController;

  public WKey(BoardView boardView, ViewerControllerInterface controller){
    super(boardView, controller);
    myController = controller;
  }

  /**
   * Adds one million score to the current game
   */
  @Override
  public void doAction(){
    myController.resetGhosts();
  }
}
