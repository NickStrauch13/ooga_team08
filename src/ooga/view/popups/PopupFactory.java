package ooga.view.popups;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;

public class PopupFactory {

  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  public static final String HIGH_SCORE_V_BOX = "HighScoreVBox";
  private ResourceBundle myResources;
  private String language;
  private UINodeFactory myNodeFactory;
  private static final String TITLE_ID = "HighScoreTitleID";
   //TODO need to put in settings resources file?
  private static final int BOX_PADDING = 50;
  private static final int BOX_SPACING = 10;
  private VBox myVBox;

  public PopupFactory(ViewerControllerInterface myController){
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
    myNodeFactory = new UINodeFactory(myController);
  }

  /**
   * Puts a popup onto the main stage that shows the high scores.
   * @param title String to have in the popup
   */
  public Popup makePopup(String title) {
    Popup scorePopup = new Popup();
    myVBox = (VBox) myNodeFactory.makeCol(HIGH_SCORE_V_BOX);
    Label scoreTitle = myNodeFactory.makeLabel(myResources.getString(title), TITLE_ID);
    myVBox.getChildren().add(scoreTitle);
    scorePopup.getContent().add(myVBox);
    return scorePopup;
  }

  /**
   * Displays the popup on the stage.
   * @param stage stage to show popup on.
   * @param scorePopup score popup Popup node
   */
  public void showPopup(Stage stage, Popup scorePopup) {
    scorePopup.show(stage);
  }

  /**
   * Adds information telling the suer how to close the window.
   * @param exitString message to show user.
   * @param id CSS ID for the message
   * @return VBox with the messages.
   */
  public VBox addExitInfo(String exitString, String id) {
    myVBox.getChildren().add(myNodeFactory.makeLabel(null, null));
    myVBox.getChildren().add(myNodeFactory.makeLabel(myResources.getString(exitString), id));
    myVBox.setPadding(new Insets(BOX_PADDING, BOX_PADDING, BOX_PADDING, BOX_PADDING));
    myVBox.setSpacing(BOX_SPACING);
    return myVBox;
  }

  /**
   * Returns the Popup Vbox
   * @return Popup VBox node
   */
  public VBox getMyVBox() {
    return myVBox;
  }

}
