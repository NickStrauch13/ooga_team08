package ooga.view.home.center;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.boardBuilder.BuilderDisplay;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.home.HomeScreen;
import ooga.view.popups.PopupFactory;

public class HomeButtons {
  private ViewerControllerInterface myController;
  private UINodeFactory myNodeBuilder;
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  private Stage myStage;
  private static final String SCORE_DIVIDER = "%s-----------%s";
  private int myHeight;
  private int myWidth;
  private String myUsername;
  private Label myPlayerNameLabel;

  public HomeButtons(ViewerControllerInterface controller, Stage stage, int width, int height){
    myController = controller;
    myNodeBuilder = new UINodeFactory(myController);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
    myStage = stage;
    myWidth = width;
    myHeight = height;
    myUsername = myResources.getString("Guest");
  }

  /**
   * Creates the buttons for the home screen display.
   * @return VBox containing all the button nodes.
   */
  public Node makeCenterButtons(){
    Button highScoresButton = myNodeBuilder.makeButton(myResources.getString("HighScores"),null, "homeScreenButton","highScoresButton",e -> displayHighScores());
    Button newGameButton = myNodeBuilder.makeButton(myResources.getString("NewGame"), null,"homeScreenButton","newGameButton",e -> startNewGame());
    Button buildBoardButton = myNodeBuilder.makeButton(myResources.getString("BuildBoard"), null,"homeScreenButton","buildBoardButton",e -> startBoardBuilder());
    Label inputText = myNodeBuilder.makeLabel(myResources.getString("userNameText"), "inputTextID");
    TextField userName = myNodeBuilder.makeInputField("userNameFieldID", e -> setUserName(e), "");
    Node row1 = myNodeBuilder.makeRow("homeColFormat", highScoresButton, newGameButton, buildBoardButton);//TODO buildBoardButton
    Node row2 = myNodeBuilder.makeRow("homeColFormat", inputText, userName);
    myPlayerNameLabel = myNodeBuilder.makeLabel(String.format(myResources.getString("PlayerLabel"), myUsername), "PlayerLabelID");
    return myNodeBuilder.makeCol("homeRowFormat", row1, row2, myPlayerNameLabel);
  }


  private void setUserName(String userName) {
    myUsername = userName;
    myController.setUsername(userName);
    myPlayerNameLabel.setText(String.format(myResources.getString("PlayerLabel"), myUsername));
  }

  private void displayHighScores(){
    PopupFactory highScoreView = new PopupFactory(myController);
    makeHighScoreView(highScoreView, myController.getScoreData());
  }

  private void makeHighScoreView(PopupFactory highScoreView, List<String[]> testList) {
    Popup scorePopup = highScoreView.makePopup("HighScoreTitle");
    addScores(testList, highScoreView.getMyVBox());
    highScoreView.addExitInfo("ExitInstructions", "ScoreExitID");
    highScoreView.showPopup(myStage, scorePopup);
  }

  private void addScores(List<String[]> scores, VBox box) {
    for(String[] score: scores){
      String scoreText = String.format(SCORE_DIVIDER, score[0], score[1]);
      Label entry = myNodeBuilder.makeLabel(scoreText, "ScoreEntryID");
      box.getChildren().add(entry);
    }
  }

  private void startNewGame() {
    readFile();
    GameDisplay gameDisplay = new GameDisplay(myStage, myWidth, myHeight, myController.getLanguage(), myController, myController.getBoardView());
    gameDisplay.setMainDisplay();
  }

  private boolean readFile(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("LoadFile");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON", "*.json"));
    File selectedFile = fileChooser.showOpenDialog(myStage);
    if (selectedFile == null) {
      return false;
    }
    else {
      myController.initializeGame(selectedFile.getPath());
      if (myController.getBoardView() == null) {
        return false;
      }
      return true;

    }
  }

  private void startBoardBuilder() {
    BuilderDisplay builderDisplay = new BuilderDisplay(myStage, myWidth, myHeight, myController);
    builderDisplay.setMainDisplay("Board Builder");
  }
}
