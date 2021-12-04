package ooga.view.popups;

import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.controller.BasicController;
import ooga.controller.Controller;
import ooga.view.UINodeFactory.UINodeFactory;

public class PopupFactory {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  private String language;
  private UINodeFactory myNodeFactory;
  private static final String TITLE_ID = "HighScoreTitleID";
   //TODO need to put in settings resources file?
  private static final int BOX_PADDING = 50;
  private static final int BOX_SPACING = 10;
  private VBox myVBox;

  public PopupFactory(BasicController myController){
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
    myNodeFactory = new UINodeFactory(myController);
  }

  /**
   * Puts a popup onto the main stage that shows the high scores.
   */
  public Popup makePopup(String title) {
    Popup scorePopup = new Popup();
    myVBox = (VBox) myNodeFactory.makeCol("HighScoreVBox");
    Label scoreTitle = myNodeFactory.makeLabel(myResources.getString(title), TITLE_ID);
    myVBox.getChildren().add(scoreTitle);
    scorePopup.getContent().add(myVBox);
    return scorePopup;
  }

  public void showPopup(Stage stage, Popup scorePopup) {
    scorePopup.show(stage);
  }

  public VBox addExitInfo(String exitString, String id) {
    myVBox.getChildren().add(myNodeFactory.makeLabel(null, null));
    myVBox.getChildren().add(myNodeFactory.makeLabel(myResources.getString(exitString), id));
    myVBox.setPadding(new Insets(BOX_PADDING, BOX_PADDING, BOX_PADDING, BOX_PADDING));
    myVBox.setSpacing(BOX_SPACING);
    return myVBox;
  }

  public VBox getMyVBox() {
    return myVBox;
  }

}
