package ooga.view.popups;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ooga.controller.Controller;

public class ErrorView {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;


  public ErrorView(String language){
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
  }

  /**
   * Displays the error as an Alert in the GUI. Displays the Title of the
   * error as well as the error message to the user.
   *
   * @param message is the String that is displayed on the Alert in the GUI
   */
  public void showError(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle(myResources.getString("ErrorTitle"));
    alert.setContentText(message);
    alert.showAndWait();
  }

}
