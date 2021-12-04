package ooga.view.gameDisplay.keyActions;

import ooga.controller.Controller;
import ooga.view.gameDisplay.center.BoardView;

/**
 * Super class that handles the view actions when the user presses keys. This abstraction
 * allows for more key actions to easily be added without modifying any closed code (just make a
 * new subclass).
 */
public abstract class KeyViewAction {
  protected static final int QUART_ROTATION = 90;
  protected static final int HALF_ROTATION = 180;
  protected static final int THREE_QUART_ROTATION = 270;

  public KeyViewAction(BoardView boardView, Controller controller){
  }

  /**
   * Handles the action in the view corresponding to the specific key pressed.
   */
  public abstract void doAction();
}
