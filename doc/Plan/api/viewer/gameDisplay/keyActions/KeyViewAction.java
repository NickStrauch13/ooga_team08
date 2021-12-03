
/**
 * Super class that handles the view actions when the user presses keys. This abstraction
 * allows for more key actions to easily be added without modifying any closed code (just make a
 * new subclass).
 */
public interface  KeyViewAction {

    public KeyViewAction(BoardView boardView){
    }

    /**
     * Handles the action in the view corresponding to the specific key pressed.
     */
   abstract void doAction();
}
