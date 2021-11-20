package ooga.view.gameDisplay.keyActions;

import javafx.scene.input.KeyCode;
import ooga.view.gameDisplay.center.BoardView;

/**
 * Super class that handles the view actions when the user presses keys. This abstraction
 * allows for more key actions to easily be added without modifying any closed code (just make a
 * new subclass).
 */
public abstract class KeyViewAction {
  protected static final int FULL_ROTATION = 360;
  protected static final int THREE_QUART_ROTATION = 270;
  protected static final int QUART_ROTATION = 90;

  public KeyViewAction(BoardView boardView){
  }

  /**
   * Handles the action in the view corresponding to the specific key pressed.
   */
  public abstract void doAction();
}
