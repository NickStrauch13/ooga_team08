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

public class HighScoreView {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  private static final String language = "English"; //TODO
  private UINodeFactory myNodeFactory;
  private static final String TITLE_ID = "HighScoreTitleID";
  private static final String SCORE_DIVIDER = "%s-----------%s"; //TODO need to put in settings resources file?
  private static final int BOX_PADDING = 50;
  private static final int BOX_SPACING = 10;

  public HighScoreView(){
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myNodeFactory = new UINodeFactory();
  }

  /**
   * Puts a popup onto the main stage that shows the high scores.
   * @param scores The list containing the username:score combinations.
   * @param stage The stage to display the popup on.
   */
  public void showHighScores(List<String[]> scores, Stage stage) {
    Popup scorePopup = new Popup();
    VBox box = makeScoreBox(scores);
    scorePopup.getContent().add(box);
    scorePopup.show(stage);
  }


  private VBox makeScoreBox(List<String[]> scores){
    Label scoreTitle = myNodeFactory.makeLabel(myResources.getString("HighScoreTitle"), TITLE_ID);
    VBox box = (VBox) myNodeFactory.makeCol("HighScoreVBox", scoreTitle);
    for(String[] score: scores){
      String scoreText = String.format(SCORE_DIVIDER, score[0], score[1]);
      Label entry = myNodeFactory.makeLabel(scoreText, "ScoreEntryID");
      box.getChildren().add(entry);
    }
    box.getChildren().add(myNodeFactory.makeLabel(null, null));
    box.getChildren().add(myNodeFactory.makeLabel(myResources.getString("ScoreExitInstructions"), "ScoreExitID"));
    box.setPadding(new Insets(BOX_PADDING, BOX_PADDING, BOX_PADDING, BOX_PADDING));
    box.setSpacing(BOX_SPACING);
    return box;
  }

}
