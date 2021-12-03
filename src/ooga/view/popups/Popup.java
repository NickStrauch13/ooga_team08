package ooga.view.popups;

import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import ooga.view.UINodeFactory.UINodeFactory;

public class Popup {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  private static final String language = "English"; //TODO
  private UINodeFactory myNodeFactory;
  private static final String TITLE_ID = "HighScoreTitleID";
   //TODO need to put in settings resources file?
  private static final int BOX_PADDING = 50;
  private static final int BOX_SPACING = 10;
  private VBox myVBox;

  public Popup(){
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myNodeFactory = new UINodeFactory();
  }

  /**
   * Puts a popup onto the main stage that shows the high scores.
   */
  public javafx.stage.Popup makePopup(String title) {
    javafx.stage.Popup scorePopup = new javafx.stage.Popup();
    myVBox = (VBox) myNodeFactory.makeCol("HighScoreVBox");
    Label scoreTitle = myNodeFactory.makeLabel(myResources.getString(title), TITLE_ID);
    myVBox.getChildren().add(scoreTitle);
    scorePopup.getContent().add(myVBox);
    return scorePopup;
  }

  public void showPopup(Stage stage, javafx.stage.Popup scorePopup) {
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
